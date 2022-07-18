package com.example.login.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants.CHARACTER_ID_KEY
import com.example.login.constants.Constants.CHARACTER_NAME_KEY
import com.example.login.constants.Constants.CHARACTER_SCREEN
import com.example.login.databinding.FragmentCharacterBinding
import com.example.login.repository.PreferenceStorage
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterFragment : BaseFragment<FragmentCharacterBinding>(R.layout.fragment_character) {

    override val viewModel: CharacterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.prevClick.observe(this) {
            navigate(R.id.levelFragment)
        }
        viewModel.nextClick.observe(this) {
            if (viewModel.radioValue.value == 0) showToast("You need to choice a class")
            else {
                PreferenceStorage(requireContext()).saveAgeScreen(CHARACTER_SCREEN, true)
                navigate(R.id.parentActivity)
            }
        }
        viewModel.radioValue.observe(this) {
            val result = requireActivity().findViewById<RadioButton>(it)
            PreferenceStorage(requireContext()).saveCharacterId(CHARACTER_ID_KEY, result?.id)
            PreferenceStorage(requireContext()).saveCharacterName(CHARACTER_NAME_KEY, result?.text.toString())
        }
    }

}