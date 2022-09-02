package com.example.login.ui.memesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.DataSource
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.data.enums.SortType
import com.example.login.data.repository.MemesRepository
import com.example.login.databinding.FragmentMemesListBinding
import com.example.login.ui.memesList.adapter.MemesAdapter
import com.example.login.ui.memesList.loadStateAdapter.MemesLoaderStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemesListFragment : BaseFragment<FragmentMemesListBinding>(R.layout.fragment_memes_list) {
    override val viewModel: MemesListViewModel by viewModel()
    private lateinit var memesAdapter: MemesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        syncByRefresh()
        initRecycler()
        initDropDownType()
        return view
    }

    private fun initRecycler() {
        memesAdapter = MemesAdapter()
        binding.memesRec.apply {
            adapter = memesAdapter.withLoadStateHeaderAndFooter(
                header = MemesLoaderStateAdapter(),
                footer = MemesLoaderStateAdapter()
            )
        }
        memesAdapter.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.memesRec.isVisible = refreshState != LoadState.Loading
            binding.gifLoading.isVisible = refreshState == LoadState.Loading
            if (refreshState is LoadState.Error) {
                snackBar(getString(R.string.smth_wrong))
            } else {
                MemesRepository().getAvailableMemes(memesAdapter.snapshot().items)
            }
        }
    }

    private fun initDropDownType() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.memes_types)
        )
        binding.autoCompleteType.setAdapter(arrayAdapter)
    }

    private fun syncByRefresh() {
        with(binding) {
            refreshLayout.setOnRefreshListener {
                memesAdapter.refresh()
                refreshLayout.isRefreshing = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.sortMemeType.observe(this){ sort ->
            if(sort == SortType.WITHOUT.sortName){
                lifecycleScope.launch {
                    viewModel.listData.collect{ data ->
                        memesAdapter.submitData(data)
                    }
                }
            } else {
                lifecycleScope.launch {
                    memesAdapter.submitData(PagingData.from(viewModel.sortByType()))
                }
            }
        }

    }
}