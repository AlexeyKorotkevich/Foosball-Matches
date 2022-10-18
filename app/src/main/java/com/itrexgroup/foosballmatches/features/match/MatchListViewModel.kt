package com.itrexgroup.foosballmatches.features.match

import android.util.Log
import com.itrexgroup.domain.usecase.MatchListUseCase
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.foosballmatches.utils.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchListViewModel @Inject constructor(
    private val matchListUseCase: MatchListUseCase
) : BaseViewModel() {

    val matchList = matchListUseCase.matchList.toLiveData()

    fun deleteMatch(id: String) {
        matchListUseCase.deleteMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnError { throwable -> Log.d("DeleteMatchError", throwable.message ?: "") }
            .subscribe()
            .also { compositeDisposable.add(it) }
    }
}