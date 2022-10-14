package com.itrexgroup.foosballmatches.usecase

import com.itrexgroup.domain.dto.ScoreDto
import com.itrexgroup.data.repository.DatabaseRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PlayerListUseCase @Inject constructor(
    databaseRepository: DatabaseRepository
) {

    private val rankByScore = BehaviorSubject.createDefault(true)

    private val playerScoreMap = databaseRepository.getMatchList()
        .map { matchList ->
            val playerMap = mutableMapOf<String, ScoreDto>()
            matchList.forEach { match ->
                if (match.playerOneName !in playerMap.keys) {
                    playerMap[match.playerOneName] = ScoreDto(gamesQuantity = 1)
                } else {
                    playerMap[match.playerOneName]?.let { it.gamesQuantity += 1 }
                }
                if (match.playerTwoName !in playerMap.keys) {
                    playerMap[match.playerTwoName] = ScoreDto(gamesQuantity = 1)
                } else {
                    playerMap[match.playerTwoName]?.let { it.gamesQuantity += 1 }
                }
                when {
                    match.playerOneScore > match.playerTwoScore -> {
                        playerMap[match.playerOneName]?.let { it.score += 1 }
                    }
                    match.playerOneScore < match.playerTwoScore -> {
                        playerMap[match.playerTwoName]?.let { it.score += 1 }
                    }
                }
            }
            return@map playerMap
        }

    val playerRankList: Observable<List<Pair<String, ScoreDto>>> =
        Observable.combineLatest(rankByScore, playerScoreMap) { byRank, player ->
            when (byRank) {
                true -> return@combineLatest player.toList().sortedByDescending {
                    it.second.score
                }
                false -> return@combineLatest player.toList().sortedByDescending {
                    it.second.gamesQuantity
                }
            }
        }

    fun toggleRankSorting() {
        //scan
        rankByScore.value?.let {
            rankByScore.onNext(!it)
        }
    }
}