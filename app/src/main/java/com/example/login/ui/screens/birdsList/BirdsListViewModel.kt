package com.example.login.ui.screens.birdsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.enumss.EmptyState
import com.example.login.data.enumss.FragNavigation
import com.example.login.data.models.BirdModel
import com.example.login.data.enumss.From
import com.example.login.data.enumss.GifState
import com.example.login.data.models.PageModel
import com.example.login.data.repository.BirdRepository
import com.example.login.data.states.NetworkResult
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class BirdsListViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val birdList: MutableLiveData<List<BirdModel>> = MutableLiveData()
    val fragmentActions: MutableLiveData<FragNavigation> = MutableLiveData()
    var gifState: MutableLiveData<GifState> = MutableLiveData()
    var emptyState: MutableLiveData<EmptyState> = MutableLiveData()
    var data = emptyFlow<NetworkResult<PageModel?>>()

    init {
        when (repo.setSearchType()) {
            From.DEFAULT -> {
                data = repo.getPosts(repo.setDefaultRequest())
                fetchDefaultResult()
            }
            From.ADVANCED -> {
                data = repo.getPosts(repo.setAdvancedRequest())
                fetchAdvancedResult()
            }
        }
        emptyState.value = EmptyState.HIDE_STATE
        birdList.value = emptyList()
    }
    private fun fetchAdvancedResult() {
        viewModelScope.launch {
            birdList.postValue(repo.searchBird(
                repo.setAdvancedRequest()
            ).birdModels)
        }
    }

    private fun fetchDefaultResult() {
        viewModelScope.launch {
            birdList.postValue(repo.searchBird(
                repo.setDefaultRequest()
            ).birdModels)
        }
    }

    fun showMore(birdModel: BirdModel) {
        fragmentActions.value = FragNavigation.SHOW_MORE
        repo.getBirdDetail(birdModel)
    }

    fun onBack(){
        fragmentActions.value = FragNavigation.BACK
    }

    fun recProperties(){
        if (birdList.value.isNullOrEmpty()) {
            emptyState.value = EmptyState.SHOW_STATE
            gifState.value = GifState.HIDE_GIF
        }
    }

}