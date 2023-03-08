package com.kriaactividade.xitiqueapp.ui.userresume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kriaactividade.xitiqueapp.R
import com.kriaactividade.xitiqueapp.databinding.FragmentUserResumeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserResumeFragment : Fragment() {

    private lateinit var binding: FragmentUserResumeBinding
    private val viewModel: UserResumeViewModel by viewModels()
    private var nextEventClicked = false
    private var currentEventClicked = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserResumeBinding.inflate(layoutInflater)
        val userResumeAdapter = UserResumeAdapter()
        val userNextResumeAdapter = UserNextResumeAdapter()

        binding.currentEventLayout.setBackgroundColor(
            resources.getColor(
                R.color.teal_700
            )
        )

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UserResumeUiState.Loading -> {

                    }
                    is UserResumeUiState.Success -> {
                        binding.apply {
                            state.uiData.balanceTotal.apply {
                                valueForYear.text = balanceForYear
                                valueCurrentEvent.text = currentBalance
                                valueNextEvent.text = nextBalance
                                progress.max = percentForTotal
                                progress.setProgress(percentCurrent, true)
                                totalProgress.text =
                                    "R$ $percentCurrent / $balanceForYear"
                                progressPercent.text = "$percent %"
                            }
                            setupAdapterForCurrentEvent(userResumeAdapter, state)
                            setupAdapterForNextEvent(userNextResumeAdapter, state)
                            cardNextEvent()
                            cardCurrentEvent()

                        }
                    }
                    is UserResumeUiState.Error -> {

                    }
                }
            }
        }
        return binding.root
    }

    private fun FragmentUserResumeBinding.setupAdapterForCurrentEvent(
        userResumeAdapter: UserResumeAdapter,
        state: UserResumeUiState.Success
    ) {
        recyclerFilter.apply {
            adapter = userResumeAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
            userResumeAdapter.submitList(state.uiData.listUserEntity)
        }
    }

    private fun FragmentUserResumeBinding.setupAdapterForNextEvent(
        userNextResumeAdapter: UserNextResumeAdapter,
        state: UserResumeUiState.Success
    ) {
        recyclerFilterNext.apply {
            adapter = userNextResumeAdapter
            layoutManager = GridLayoutManager(requireContext(), 1)
            userNextResumeAdapter.submitList(state.uiData.listNext)
        }
    }

    private fun cardNextEvent() {
        binding.nextEventLayout.setOnClickListener {
            nextEventClicked = if (nextEventClicked) {
                binding.nextEventLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.white,
                        null
                    )
                )
                binding.recyclerFilterNext.visibility = View.GONE
                false
            } else {
                binding.nextEventLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.teal_700,
                        null
                    )
                )
                binding.recyclerFilterNext.visibility = View.VISIBLE
                true
            }
        }
    }

    private fun cardCurrentEvent() {
        binding.currentEventLayout.setOnClickListener {
            currentEventClicked = if (currentEventClicked) {
                binding.currentEventLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.white,
                        null
                    )
                )
                binding.recyclerFilter.visibility = View.GONE
                false
            } else {
                binding.currentEventLayout.setBackgroundColor(
                    resources.getColor(
                        R.color.teal_700,
                        null
                    )
                )
                binding.recyclerFilter.visibility = View.VISIBLE
                true
            }
        }
    }

}