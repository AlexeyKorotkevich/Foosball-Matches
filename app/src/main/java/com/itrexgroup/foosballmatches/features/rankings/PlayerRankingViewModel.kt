package com.itrexgroup.foosballmatches.features.rankings

import com.itrexgroup.domain.dto.ScoreDto
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.foosballmatches.usecase.PlayerListUseCase
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class PlayerRankingViewModel @Inject constructor(
    private val playerListUseCase: PlayerListUseCase
) : BaseViewModel() {

    val playerRankedList = BehaviorSubject.createDefault<List<Pair<String, ScoreDto>>>(emptyList())

    fun toggleRankSorting() {
        playerListUseCase.toggleRankSorting()
    }

    init {
        playerListUseCase.playerRankList
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe { list ->
                playerRankedList.onNext(list)
            }.also { compositeDisposable.add(it) }
    }
}