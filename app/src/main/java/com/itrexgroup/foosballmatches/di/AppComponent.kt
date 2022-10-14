package com.itrexgroup.foosballmatches.di

import android.app.Application
import com.itrexgroup.foosballmatches.di.modules.*
import com.itrexgroup.foosballmatches.features.match.MatchListFragment
import com.itrexgroup.foosballmatches.features.match.edit_match_dialog.EditMatchDialogFragment
import com.itrexgroup.foosballmatches.features.rankings.PlayerRankingFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        DatabaseModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

    fun inject(matchListFragment: MatchListFragment)
    fun inject(playerRankingFragment: PlayerRankingFragment)
    fun inject(editMatchDialog: EditMatchDialogFragment)
}