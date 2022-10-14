package com.itrexgroup.foosballmatches.features.match.edit_match_dialog

import androidx.lifecycle.MutableLiveData
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.domain.usecase.MatchListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EditMatchViewModel @Inject constructor(
    private val matchListUseCase: MatchListUseCase
) : BaseViewModel() {

    val id = BehaviorSubject.create<String>()
    val playerOneName = BehaviorSubject.createDefault("")
    val playerTwoName = BehaviorSubject.createDefault("")
    val playerOneScore = BehaviorSubject.createDefault(0)
    val playerTwoScore = BehaviorSubject.createDefault(0)

    val isSaveButtonEnabled = MutableLiveData<Boolean>()

    private fun checkIsSaveEnabled() {
        isSaveButtonEnabled.value =
            !playerOneName.value.isNullOrEmpty() && !playerTwoName.value.isNullOrEmpty()
    }

    fun onPlayerOneName(name: String) {
        playerOneName.onNext(name)
        checkIsSaveEnabled()
    }

    fun onPlayerTwoName(name: String) {
        playerTwoName.onNext(name)
        checkIsSaveEnabled()
    }

    fun onPlayerOneScore(score: Int) {
        playerOneScore.onNext(score)
    }

    fun onPlayerTwoScore(score: Int) {
        playerTwoScore.onNext(score)
    }

    fun getMatchData(
        id: String,
        playerOneName: String,
        playerOneScore: Int,
        playerTwoName: String,
        playerTwoScore: Int
    ) {
        this.id.onNext(id)
        this.playerOneName.onNext(playerOneName)
        this.playerTwoName.onNext(playerTwoName)
        this.playerOneScore.onNext(playerOneScore)
        this.playerTwoScore.onNext(playerTwoScore)
        checkIsSaveEnabled()
    }

    fun updateMatch() {
        matchListUseCase.updateMatch(
            MatchDto(
                id.value ?: "",
                playerOneName.value!!,
                playerOneScore.value!!,
                playerTwoName.value!!,
                playerTwoScore.value!!
            )
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}