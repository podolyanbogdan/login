package com.example.login.ui.screens.birdsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.BirdModel
import com.example.login.data.PageModel
import com.example.login.data.enumss.From
import com.example.login.data.repository.BirdRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class BirdsListViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val birdList: MutableLiveData<List<BirdModel>> = MutableLiveData()
    val response: MutableLiveData<Response<PageModel>> = MutableLiveData()

    val showMoreTrigger: MutableLiveData<Unit> = MutableLiveData()
    val showLoadingGif: MutableLiveData<Boolean> = MutableLiveData()

    init {
        when (repo.setSearchType()) {
            From.DEFAULT -> fetchDefaultResult()
            From.ADVANCED -> fetchAdvancedResult()
        }
        birdList.value = emptyList()
        showLoadingGif.value = !birdList.value.isNullOrEmpty()
    }

    private fun requestString(): String {
        val result = mutableMapOf<String, String>()

        if (repo.setSearchRequest().country.isNotEmpty()) {
            result["cnt"] = repo.setSearchRequest().country
        }
        if (repo.setSearchRequest().gen.isNotEmpty()) {
            result["gen"] = repo.setSearchRequest().gen
        }
        if (repo.setSearchRequest().type.isNotEmpty()) {
            result["sp"] = repo.setSearchRequest().type
        }
        if (repo.setSearchRequest().subtype.isNotEmpty()) {
            result["ssp"] = repo.setSearchRequest().subtype
        }
        if (repo.setSearchRequest().soundType.isNotEmpty()) {
            result["type"] = repo.setSearchRequest().soundType
        }
        if (repo.setSearchRequest().place.isNotEmpty()) {
            result["loc"] = repo.setSearchRequest().place
        }
        if (repo.setSearchRequest().remarks.isNotEmpty()) {
            result["rmk"] = repo.setSearchRequest().remarks
        }
        if (repo.setSearchRequest().author.isNotEmpty()) {
            result["rec"] = repo.setSearchRequest().author
        }

        return result.entries.joinToString(separator = " ").replace("=", ":")
    }

    private fun fetchAdvancedResult() {
        viewModelScope.launch {
            response.postValue(
                repo.getResponseTest(
                    requestString()
                )
            )

            birdList.postValue(
                repo.getAdvancedSearch(
                    requestString()
                ).birdModels
            )
        }
    }

    private fun fetchDefaultResult() {
        viewModelScope.launch {
            birdList.postValue(
                repo.getDefaultSearch(repo.setDefaultRequest()).birdModels
            )
        }
    }

    fun showMore(birdModel: BirdModel) {
        showMoreTrigger.value = Unit
        repo.getBirdDetail(birdModel)
    }
}