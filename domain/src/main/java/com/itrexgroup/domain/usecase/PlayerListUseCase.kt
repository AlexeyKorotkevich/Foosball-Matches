package com.itrexgroup.domain.usecase

import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.domain.dto.PlayerDto
import com.itrexgroup.domain.dto.ScoreDto
import com.itrexgroup.domain.repository.DatabaseRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class PlayerListUseCase(
    databaseRepository: DatabaseRepository
) {

    private val rankByScore = BehaviorSubject.createDefault(true)
    private val rankByScoreScan = rankByScore.scan { prevValue, _ ->
        !prevValue
    }

    fun toggleRankSorting() {
        rankByScore.onNext(true)
    }

    private val playerScoreList = databaseRepository.getMatchList()
        .map { matchList ->
            return@map matchList.calculatePlayerGames()
        }

    val playerRankList: Observable<List<PlayerDto>> =
        Observable.combineLatest(rankByScoreScan, playerScoreList) { byRank, player ->
            when (byRank) {
                true -> return@combineLatest player.sortedByDescending {
                    it.score.wins
                }
                false -> return@combineLatest player.sortedByDescending {
                    it.score.gamesQuantity
                }
            }
        }

    private fun List<MatchDto>.calculatePlayerGames(): List<PlayerDto> {
        val playerList = mutableListOf<PlayerDto>()
        this.forEach { match ->
            if (playerList.find { it.name == match.playerOneName } == null) {
                playerList.add(PlayerDto(match.playerOneName, ScoreDto(gamesQuantity = 1)))
            } else {
                playerList.find { it.name == match.playerOneName }
                    ?.let { it.score.gamesQuantity += 1 }
            }
            if (playerList.find { it.name == match.playerTwoName } == null) {
                playerList.add(PlayerDto(match.playerTwoName, ScoreDto(gamesQuantity = 1)))
            } else {
                playerList.find { it.name == match.playerTwoName }
                    ?.let { it.score.gamesQuantity += 1 }
            }
            when {
                match.playerOneScore > match.playerTwoScore -> {
                    playerList.find { it.name == match.playerOneName }
                        ?.let { it.score.wins += 1 }
                }
                match.playerOneScore < match.playerTwoScore -> {
                    playerList.find { it.name == match.playerTwoName }
                        ?.let { it.score.wins += 1 }
                }
            }
        }
        return playerList
    }
}