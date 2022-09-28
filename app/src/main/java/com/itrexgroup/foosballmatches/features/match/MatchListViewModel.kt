package com.itrexgroup.foosballmatches.features.match

import com.itrexgroup.domain.dto.MatchDto
import com.itrexgroup.foosballmatches.base.BaseViewModel
import com.itrexgroup.foosballmatches.usecase.MatchListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MatchListViewModel @Inject constructor(
    private val matchListUseCase: MatchListUseCase
) : BaseViewModel() {

    val matchList = BehaviorSubject.createDefault<List<MatchDto>>(emptyList())

    fun deleteMatch(id: String) {
        matchListUseCase.deleteMatch(id)
    }

    init {
        matchListUseCase.matchList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { list ->
                matchList.onNext(list)
            }.also { compositeDisposable.add(it) }
    }
}