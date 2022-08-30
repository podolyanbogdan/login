package com.example.login.ui.introducingFragments.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentParentPageBinding
import com.example.login.ui.introducingFragments.first.FirstIntroduceFragment
import com.example.login.ui.introducingFragments.parent.adapter.ViewPagerAdapter
import com.example.login.ui.introducingFragments.second.SecondIntroduceFragment
import com.example.login.ui.introducingFragments.second.SecondIntroduceViewModel
import com.example.login.ui.introducingFragments.thirth.ThirdIntroduceFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ParentPageFragment : BaseFragment<FragmentParentPageBinding>(R.layout.fragment_parent_page) {
    override val viewModel: ParentPageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(FirstIntroduceFragment(), "Info")
        adapter.addFragment(SecondIntroduceFragment(), "Introducing")
        adapter.addFragment(ThirdIntroduceFragment(), "How to")

        binding.searchPager.adapter = adapter
        binding.searchTabs.setupWithViewPager(binding.searchPager)
        return view
    }

}