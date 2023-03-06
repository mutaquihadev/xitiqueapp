package com.kriaactividade.xitiqueapp.ui.userresume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kriaactividade.xitiqueapp.databinding.FragmentUserResumeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserResumeFragment : Fragment() {

    private lateinit var binding: FragmentUserResumeBinding
    private val viewModel: UserResumeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserResumeBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.uiState.collect {
                it.forEach { balance ->
                    binding.apply {
                        valueForYear.text = balance.balanceForYear
                        valueCurrentEvent.text = balance.currentBalance
                        valueNextEvent.text = balance.nextBalance
                        progress.max = balance.percentForTotal
                        progress.setProgress(balance.percentCurrent, true)
                        totalProgress.text =
                            "R$ ${balance.percentCurrent} / ${balance.balanceForYear}"
                        progressPercent.text = "${balance.percent} %"
                    }
                }
            }
        }
        return binding.root
    }

}