package com.example.login.ui.screens.birdsList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.enumss.EmptyState
import com.example.login.data.enumss.FragNavigation
import com.example.login.data.enumss.GifState
import com.example.login.data.localeModels.BirdModelLocale
import com.example.login.data.mapper.BirdMapper
import com.example.login.data.mapper.PictureMapper
import com.example.login.data.models.PageModel
import com.example.login.data.repository.BirdRepository
import com.example.login.data.states.ResponseState
import com.example.login.ui.screens.birdsList.adapter.RecyclerActions
import kotlinx.coroutines.launch
import retrofit2.Response

class BirdsListViewModel(
    private val repo: BirdRepository,
) : BaseViewModel(), RecyclerActions {

    private val searchResponse: MutableLiveData<ResponseState<PageModel>> = MutableLiveData()
    val updateList: MutableLiveData<List<BirdModelLocale>> = MutableLiveData()
    val showError: MutableLiveData<Unit> = MutableLiveData()
    val fragmentActions: MutableLiveData<FragNavigation> = MutableLiveData()
    var gifState: MutableLiveData<GifState> = MutableLiveData()
    var emptyState: MutableLiveData<EmptyState> = MutableLiveData()

    init {
        emptyState.value = EmptyState.HIDE_STATE
        gifState.value = GifState.SHOW_GIF
    }

    //advanced search
    fun getAdvancedSearchResponse(
        value: String
    ) = viewModelScope.launch {
        searchResponse.postValue(ResponseState.Loading())
        val response = repo.getBirdResponse(
            value
        )
        searchResponse.postValue(handleAdvancedSearchResponse(response))
    }

    fun getDefaultSearchResponse(
        value: String
    ) = viewModelScope.launch {
        searchResponse.postValue(ResponseState.Loading())
        val response = repo.getBirdResponse(
            value
        )
        searchResponse.postValue(handleAdvancedSearchResponse(response))
    }

    //resultResponse.birdModels
    private fun handleAdvancedSearchResponse(response: Response<PageModel>): ResponseState<PageModel> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                recProperties(
                    BirdMapper(
                        PictureMapper()
                    ).map(resultResponse.birdModels)
                )
            }
        } else {
            showError()
        }
        return ResponseState.Error(response.message())
    }
    //advanced search

    override fun showMore(birdModel: BirdModelLocale) {
        fragmentActions.value = FragNavigation.SHOW_MORE
        repo.getBirdDetail(birdModel)
    }

    fun onBack() {
        fragmentActions.value = FragNavigation.BACK
    }

    private fun recProperties(list: List<BirdModelLocale>) {
        if (list.isEmpty()) {
            emptyState.value = EmptyState.SHOW_STATE
            gifState.value = GifState.HIDE_GIF
        }
        gifState.value = GifState.HIDE_GIF
        updateList.value = list
    }

    private fun showError() {
        gifState.value = GifState.HIDE_GIF
        showError.value = Unit
    }
}