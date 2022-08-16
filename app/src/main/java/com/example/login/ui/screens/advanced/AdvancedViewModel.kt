package com.example.login.ui.screens.advanced

import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.SearchModel
import com.example.login.data.enumss.From
import com.example.login.data.repository.BirdRepository

class AdvancedViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val onSearchTrigger = SingleLiveEvent<Unit>()
    var searchModel = SearchModel()

    private fun createSearchRequest() {
        repo.getSearchType(From.ADVANCED)
        if(searchModel.country == "Without Country"){
            searchModel.country = ""
        }
        repo.getSearchRequest(searchModel)
    }

    fun onSearch() {
        createSearchRequest()
        onSearchTrigger.value = Unit
    }
}