package com.itrexgroup.foosballmatches.features.rankings

import androidx.lifecycle.MutableLiveData
import com.itrexgroup.domain.dto.ScoreDto
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.domain.usecase.PlayerListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerRankingViewModel @Inject constructor(
    private val playerListUseCase: PlayerListUseCase
) : BaseViewModel() {

    val playerRankedList = MutableLiveData<List<Pair<String, ScoreDto>>>()

    fun toggleRankSorting() {
        playerListUseCase.toggleRankSorting()
    }

    init {
        playerListUseCase.playerRankList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list ->
                playerRankedList.value = list
            }.also { compositeDisposable.add(it) }
    }
}