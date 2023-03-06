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
            viewModel.uiState.collect{
                it.forEach {balance ->
                    binding.valueForYear.text = balance.balanceForYear
                    binding.valueCurrentEvent.text = balance.currentBalance
                    binding.valueNextEvent.text = balance.nextBalance
                    binding.progress.max = balance.percentForTotal
                    binding.progress.setProgress(balance.percentCurrent,true)
                    binding.totalProgress.text = "R$ ${balance.percentCurrent} / ${balance.balanceForYear}"
                    binding.progressPercent.text = "${balance.percent} %"
                }
            }
        }
        return binding.root
    }

}