package com.itrexgroup.foosballmatches.features.match

import androidx.lifecycle.MutableLiveData
import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.foosballmatches.usecase.MatchListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchListViewModel @Inject constructor(
    private val matchListUseCase: MatchListUseCase
) : BaseViewModel() {

    val matchList = MutableLiveData<List<MatchDto>>()

    fun deleteMatch(id: String) {
        matchListUseCase.deleteMatch(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    init {
        matchListUseCase.matchList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list ->
                matchList.value = list
            }.also { compositeDisposable.add(it) }
    }
}