package com.example.login.ui.age

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentAgeBinding
import com.example.login.ui.age.enums.AgeType
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgeFragment : BaseFragment<FragmentAgeBinding>(R.layout.fragment_age) {
    override val viewModel: AgeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.currentImage.observe(viewLifecycleOwner) { type ->
            hideKeyboard()
            when (type) {
                AgeType.THREE_M -> binding.ageImage.setImageResource(R.drawable.three)
                AgeType.ELEVENTH_M -> binding.ageImage.setImageResource(R.drawable.elevent)
                AgeType.SIXTEEN_M -> binding.ageImage.setImageResource(R.drawable.sixteen)
                AgeType.THIRTY_FIVE_M -> binding.ageImage.setImageResource(R.drawable.thridt_five)
                AgeType.FOURTH_M -> binding.ageImage.setImageResource(R.drawable.fourth)
                AgeType.SIXTEE_M -> binding.ageImage.setImageResource(R.drawable.sixtht)

                AgeType.THREE_F -> binding.ageImage.setImageResource(R.drawable.three_f)
                AgeType.ELEVENTH_F -> binding.ageImage.setImageResource(R.drawable.twelth_f)
                AgeType.TWENTY_F -> binding.ageImage.setImageResource(R.drawable.twelth_f)
                AgeType.THIRTY_FIVE_F -> binding.ageImage.setImageResource(R.drawable.thirt_five_f)
                AgeType.FOURTH_F -> binding.ageImage.setImageResource(R.drawable.fourth_f)
                AgeType.SIXTEE_F -> binding.ageImage.setImageResource(R.drawable.old_f)
                else -> {}
            }
        }
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let { view ->
            val imm = requireContext().getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}