package com.itrexgroup.foosballmatches.features.match

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.itrexgroup.foosballmatches.R
import com.itrexgroup.foosballmatches.appComponent
import com.itrexgroup.foosballmatches.base.BaseFragment
import com.itrexgroup.foosballmatches.databinding.FragmentMatchListBinding
import com.itrexgroup.foosballmatches.features.match.edit_match_dialog.EditMatchDialogFragment
import com.itrexgroup.foosballmatches.utils.injectViewModel
import javax.inject.Inject

class MatchListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MatchListViewModel
    private lateinit var binding: FragmentMatchListBinding
    private val matchListAdapter = MatchListAdapter()

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
        binding = FragmentMatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeData() {
        compositeDisposable.add(
            viewModel.matchList.subscribe { list ->
                matchListAdapter.repopulate(list)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchListBinding.bind(view)
        setTitle(getString(R.string.match_list_title))
        setRecyclerAdapter()
        setMenuFunctionality()
        setFloatingButtonFunctionality()
    }

    private fun setRecyclerAdapter() {
        binding.matchRecyclerView.layoutManager = LinearLayoutManager(context)
        matchListAdapter.setItemClick { item ->
            val editMatchDialog = EditMatchDialogFragment.getInstance(
                matchId = item.id,
                playerOneName = item.playerOneName,
                playerTwoName = item.playerTwoName,
                playerOneScore = item.playerOneScore,
                playerTwoScore = item.playerTwoScore
            )
            editMatchDialog.show(
                requireActivity().supportFragmentManager,
                EditMatchDialogFragment.TAG
            )
        }
        matchListAdapter.setLongClick { item ->
            showDeleteMatchAlertDialog(onDeleteDialog = {
                viewModel.deleteMatch(item.id)
            })
        }
        binding.matchRecyclerView.adapter = matchListAdapter
    }

    private fun setMenuFunctionality() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.match_list_options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.player_rank_button -> {
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_matchListFragment_to_playerRankingFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setFloatingButtonFunctionality() {
        binding.fbAddMatch.setOnClickListener {
            val editMatchDialog = EditMatchDialogFragment.getInstance()
            editMatchDialog.show(
                requireActivity().supportFragmentManager,
                EditMatchDialogFragment.TAG
            )
        }
    }

    private fun showDeleteMatchAlertDialog(
        onDeleteDialog: () -> Unit
    ) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle("Delete match")
        alertDialog.setMessage("Are you sure you want to delete this match?")
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { dialog, _ ->
            onDeleteDialog.invoke()
            dialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}