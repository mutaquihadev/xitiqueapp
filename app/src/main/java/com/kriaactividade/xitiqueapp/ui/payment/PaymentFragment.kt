package com.kriaactividade.xitiqueapp.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kriaactividade.xitiqueapp.databinding.FragmentPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(layoutInflater)

        setClickInCalculator()
        clickToClearValues()

        return binding.root
    }

    private fun clickToClearValues() {
        binding.clearText.setOnClickListener {
            if (binding.textNotifications.text.isNotEmpty()) {
                val size = 0
                val sizeString = binding.textNotifications.length() - 1
                val textClear = binding.textNotifications.text.substring(size, sizeString)
                binding.textNotifications.text = textClear
            }
        }
    }

    private fun setClickInCalculator() {
        binding.layoutCalculator.apply {
            btnZero.setOnClickListener {
                setValueInCalculator("0")
            }
            btnOne.setOnClickListener {
                setValueInCalculator("1")
            }
            btnTwo.setOnClickListener {
                setValueInCalculator("2")
            }
            btnThree.setOnClickListener {
                setValueInCalculator("3")
            }
            btnFour.setOnClickListener {
                setValueInCalculator("4")
            }
            btnFive.setOnClickListener {
                setValueInCalculator("5")
            }
            btnSix.setOnClickListener {
                setValueInCalculator("6")
            }
            btnSeven.setOnClickListener {
                setValueInCalculator("7")
            }
            btnEight.setOnClickListener {
                setValueInCalculator("8")
            }
            btnNine.setOnClickListener {
                setValueInCalculator("9")
            }
        }
    }

    private fun setValueInCalculator(value: String) {
        binding.textNotifications.append(value)
    }

}