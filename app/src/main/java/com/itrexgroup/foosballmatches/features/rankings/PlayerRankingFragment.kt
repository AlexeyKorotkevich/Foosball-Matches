package com.itrexgroup.foosballmatches.features.rankings

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.itrexgroup.foosballmatches.R
import com.itrexgroup.foosballmatches.appComponent
import com.itrexgroup.foosballmatches.base.BaseFragment
import com.itrexgroup.foosballmatches.databinding.FragmentPlayerRankingBinding
import javax.inject.Inject

class PlayerRankingFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: PlayerRankingViewModel

    private lateinit var binding: FragmentPlayerRankingBinding
    private val playerRankingAdapter = PlayerRankingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerRankingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeData() {
        viewModel.playerRankedList.observe(this) { list ->
            playerRankingAdapter.repopulate(list)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayerRankingBinding.bind(view)
        setTitle(getString(R.string.player_ranking_title))
        setRecyclerAdapter()
        setMenuFunctionality()
    }

    private fun setRecyclerAdapter() {
        binding.playersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.playersRecyclerView.adapter = playerRankingAdapter
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