package com.itrexgroup.foosballmatches.features.match.edit_match_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.itrexgroup.foosballmatches.appComponent
import com.itrexgroup.foosballmatches.base.BaseDialogFragment
import com.itrexgroup.foosballmatches.databinding.EditMatchDialogBinding
import com.itrexgroup.foosballmatches.utils.Constants
import com.itrexgroup.foosballmatches.utils.injectViewModel
import javax.inject.Inject

class EditMatchDialogFragment : BaseDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: EditMatchViewModel
    private lateinit var binding: EditMatchDialogBinding

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
        binding = EditMatchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun observeData() {
        viewModel.isSaveButtonEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.saveButton.isEnabled = enabled
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditMatchDialogBinding.bind(view)
        getArgsData()
        setEditTextFieldsFunctionality()
        setSaveButtonFunctionality()
    }

    private fun setEditTextFieldsFunctionality() {
        binding.playerOneName.setText(viewModel.playerOneName.value)
        binding.playerOneName.doOnTextChanged { text, _, _, _ ->
            viewModel.onPlayerOneName(text.toString())
        }
        binding.playerTwoName.setText(viewModel.playerTwoName.value)
        binding.playerTwoName.doOnTextChanged { text, _, _, _ ->
            viewModel.onPlayerTwoName(text.toString())
        }
        binding.playerOneScore.setText(viewModel.playerOneScore.value.toString())
        binding.playerOneScore.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                viewModel.onPlayerOneScore(text.toString().toInt())
            }
        }
        binding.playerTwoScore.setText(viewModel.playerTwoScore.value.toString())
        binding.playerTwoScore.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                viewModel.onPlayerTwoScore(text.toString().toInt())
            }
        }
    }

    private fun setSaveButtonFunctionality() {
        binding.saveButton.setOnClickListener {
            viewModel.updateMatch()
            this.dismiss()
        }
    }

    private fun getArgsData() {
        arguments?.let { args ->
            viewModel.getMatchData(
                id = args.getString(Constants.ARG_MATCH_ID, ""),
                playerOneName = args.getString(Constants.ARG_PLAYER_ONE_NAME, ""),
                playerTwoName = args.getString(Constants.ARG_PLAYER_TWO_NAME, ""),
                playerOneScore = args.getInt(Constants.ARG_PLAYER_ONE_SCORE, 0),
                playerTwoScore = args.getInt(Constants.ARG_PLAYER_TWO_SCORE, 0)
            )
        }
    }

    companion object {
        const val TAG = "EditMatchDialogFragment"

        @JvmStatic
        fun getInstance(
            matchId: String = System.currentTimeMillis().toString(),
            playerOneName: String? = null,
            playerTwoName: String? = null,
            playerOneScore: Int? = null,
            playerTwoScore: Int? = null
        ): EditMatchDialogFragment {
            return EditMatchDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.ARG_MATCH_ID, matchId)
                    putString(Constants.ARG_PLAYER_ONE_NAME, playerOneName)
                    putString(Constants.ARG_PLAYER_TWO_NAME, playerTwoName)
                    putInt(Constants.ARG_PLAYER_ONE_SCORE, playerOneScore ?: 0)
                    putInt(Constants.ARG_PLAYER_TWO_SCORE, playerTwoScore ?: 0)
                }
            }
        }
    }
}