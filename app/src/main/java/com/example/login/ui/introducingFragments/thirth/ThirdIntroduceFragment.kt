package com.example.login.ui.introducingFragments.thirth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentThirdIntroduceBinding
import com.example.login.ui.introducingFragments.parent.ParentPageFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdIntroduceFragment : BaseFragment<FragmentThirdIntroduceBinding>(R.layout.fragment_third_introduce) {
    override val viewModel: ThirdIntroduceViewModel by viewModel()

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
        viewModel.initEvent.observe(this){
            navigate(ParentPageFragmentDirections.fromParentPageToBottomActivity())
        }
    }
}