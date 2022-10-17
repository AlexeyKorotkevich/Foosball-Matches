package com.itrexgroup.foosballmatches.features.rankings

import com.itrexgroup.domain.usecase.PlayerListUseCase
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.foosballmatches.utils.toLiveData
import javax.inject.Inject

class PlayerRankingViewModel @Inject constructor(
    private val playerListUseCase: PlayerListUseCase
) : BaseViewModel() {

    val playerRankedList = playerListUseCase.playerRankList.toLiveData()

    fun toggleRankSorting() {
        playerListUseCase.toggleRankSorting()
    }
}