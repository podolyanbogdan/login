package com.example.login.ui.level

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants
import com.example.login.constants.Constants.LEVEL_SCREEN
import com.example.login.databinding.FragmentLevelBinding
import com.example.login.repository.MyRepository
import com.example.login.repository.PreferenceStorage
import org.koin.androidx.viewmodel.ext.android.viewModel


class LevelFragment : BaseFragment<FragmentLevelBinding>(R.layout.fragment_level) {
    override val viewModel: LevelViewModel by viewModel()

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
        viewModel.prevClick.observe(this){
            navigate(R.id.ageFragment)
        }
        viewModel.nextClick.observe(this){
            if(viewModel.levelValue.value == "0") showToast("You need to choice your level!")
            else {
                PreferenceStorage(requireContext()).saveLevelScreen(LEVEL_SCREEN, true)
                navigate(R.id.characterFragment)
            }
        }
    }
}