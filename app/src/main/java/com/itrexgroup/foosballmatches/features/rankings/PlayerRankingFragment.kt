package com.itrexgroup.foosballmatches.features.rankings

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.itrexgroup.foosballmatches.R
import com.itrexgroup.foosballmatches.appComponent
import com.itrexgroup.foosballmatches.base.BaseFragment
import com.itrexgroup.foosballmatches.databinding.FragmentPlayerRankingBinding
import com.itrexgroup.foosballmatches.utils.injectViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PlayerRankingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PlayerRankingViewModel
    private lateinit var binding: FragmentPlayerRankingBinding
    private lateinit var playerRankingAdapter: PlayerRankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel = requireActivity().injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMenuFunctionality()

        binding = FragmentPlayerRankingBinding.bind(view)
        binding.playersRecyclerView.layoutManager = LinearLayoutManager(context)
        playerRankingAdapter = PlayerRankingAdapter()
        binding.playersRecyclerView.adapter = playerRankingAdapter

        compositeDisposable.add(
            viewModel.playerRankedList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                playerRankingAdapter.repopulate(list)
            }
        )
    }

    private fun setMenuFunctionality() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.player_ranking_options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.ranking_sort_button -> {
                        viewModel.toggleRankSorting()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}