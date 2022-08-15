package com.example.login.ui.screens.birdsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.BirdModel
import com.example.login.data.repository.BirdRepository
import kotlinx.coroutines.launch

class BirdsListViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val birdList: MutableLiveData<List<BirdModel>> = MutableLiveData()
    val showMoreTrigger: MutableLiveData<Unit> = MutableLiveData()
    val showLoadingGif: MutableLiveData<Boolean> = MutableLiveData()

    init {
        birdList.value = emptyList()
        fetchAdvancedResult()
        showLoadingGif.value = !birdList.value.isNullOrEmpty()
    }

    private fun fetchAdvancedResult() {
        viewModelScope.launch {
            birdList.postValue(repo.getAdvancedSearch().birdModels)
        }
    }

    fun showMore(birdModel: BirdModel) {
        showMoreTrigger.value = Unit
        repo.getBirdDetail(birdModel)
    }
}