package com.example.login.ui.screens.birdsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.EmptyState
import com.example.login.data.enumss.FragNavigation
import com.example.login.data.enumss.GifState
import com.example.login.data.models.BirdModel
import com.example.login.data.states.NetworkResult
import com.example.login.databinding.FragmentBirdsListBinding
import com.example.login.ui.screens.birdsList.adapter.BirdsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirdsListFragment : BaseFragment<FragmentBirdsListBinding>(R.layout.fragment_birds_list) {
    override val viewModel: BirdsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigate(R.id.tabFragment)
        }
        getPosts()
        return view
    }

    private fun getPosts() {
        lifecycleScope.launchWhenStarted {
            viewModel.data.collect() {
                when (it) {
                    is NetworkResult.Success -> {
                        initRecycler()
                        viewModel.gifState.value = GifState.HIDE_GIF
                    }
                    is NetworkResult.Failure -> {
                        showToast(getString(R.string.request_was_failed))
                    }
                    NetworkResult.Loading -> {
                        viewModel.gifState.value = GifState.SHOW_GIF
                    }
                }
            }
        }
    }

    private fun initRecycler() {
        viewModel.recProperties()
        binding.recBirds.also { rec ->
            rec.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rec.adapter = BirdsAdapter(
                birdsList = viewModel.birdList.value as List<BirdModel>,
                showMore = { viewModel.showMore(it) }
            )
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.fragmentActions.observe(this) { act ->
            when(act){
                FragNavigation.SHOW_MORE -> navigate(R.id.detailsFragment)
                FragNavigation.BACK -> navigate(R.id.tabFragment)
            }
        }
        viewModel.birdList.observe(this) { list ->
            if (list.isNotEmpty()) {
                viewModel.emptyState.value = EmptyState.HIDE_STATE
                initRecycler()
            }
        }
    }

}