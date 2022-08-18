package com.example.login.ui.screens.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentTabBinding
import com.example.login.ui.screens.tab.adapter.ViewPagerAdapter
import com.example.login.ui.screens.advanced.AdvancedFragment
import com.example.login.ui.screens.defaultt.DefaultFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class TabFragment : BaseFragment<FragmentTabBinding>(R.layout.fragment_tab) {
    override val viewModel: TabViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(DefaultFragment(), "Search")
        adapter.addFragment(AdvancedFragment(), "Advanced")

        binding.searchPager.adapter = adapter
        binding.searchTabs.setupWithViewPager(binding.searchPager)

        return view
    }

}