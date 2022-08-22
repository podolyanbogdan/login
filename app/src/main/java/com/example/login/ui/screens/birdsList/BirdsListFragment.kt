package com.example.login.ui.screens.birdsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.FragNavigation
import com.example.login.data.enumss.From
import com.example.login.data.enumss.GifState
import com.example.login.data.states.ResponseState
import com.example.login.databinding.FragmentBirdsListBinding
import com.example.login.ui.screens.birdsList.adapter.BirdsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirdsListFragment : BaseFragment<FragmentBirdsListBinding>(R.layout.fragment_birds_list) {
    override val viewModel: BirdsListViewModel by viewModel()
    private lateinit var newsAdapter: BirdsAdapter
    private val args: BirdsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initRecycler()
        fetchRequest()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigate(BirdsListFragmentDirections.fromBirdsListToTabsFrag())
        }
        return view
    }

    private fun fetchRequest() {
        when (args.querySearchType) {
            From.DEFAULT -> {
                viewModel.getDefaultSearchResponse(args.queryString)
            }
            From.ADVANCED -> {
                viewModel.getAdvancedSearchResponse(args.queryString)
            }
        }
    }

    private fun initRecycler() {
        newsAdapter = BirdsAdapter(viewModel)
        binding.recBirds.apply {
            adapter = newsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.fragmentActions.observe(this) { act ->
            when (act) {
                FragNavigation.SHOW_MORE -> navigate(BirdsListFragmentDirections.fromBirdListToDetails())
                FragNavigation.BACK -> navigate(BirdsListFragmentDirections.fromBirdsListToTabsFrag())
            }
        }
        viewModel.updateList.observe(this) { list ->
            newsAdapter.submitList(list)
        }
        viewModel.showError.observe(this) {
            showToast(getString(R.string.smth_went_wrong))
        }
    }
}