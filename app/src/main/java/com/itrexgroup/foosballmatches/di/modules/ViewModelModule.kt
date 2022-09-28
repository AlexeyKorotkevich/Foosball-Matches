package com.itrexgroup.foosballmatches.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itrexgroup.foosballmatches.di.ViewModelFactory
import com.itrexgroup.foosballmatches.di.utils.ViewModelKey
import com.itrexgroup.foosballmatches.features.match.MatchListViewModel
import com.itrexgroup.foosballmatches.features.match.edit_match_dialog.EditMatchViewModel
import com.itrexgroup.foosballmatches.features.rankings.PlayerRankingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MatchListViewModel::class)
    abstract fun bindMatchListViewModel(viewModel: MatchListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditMatchViewModel::class)
    abstract fun bindEditMatchViewModel(viewModel: EditMatchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerRankingViewModel::class)
    abstract fun bindPlayerRankingViewModel(viewModel: PlayerRankingViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}