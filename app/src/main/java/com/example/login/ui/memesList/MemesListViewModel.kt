package com.example.login.ui.memesList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.login.arch.BaseViewModel
import com.example.login.data.enums.SortType
import com.example.login.data.models.localeModels.MemeLocale
import com.example.login.ui.memesList.pagination.MemesPagingSource
import com.example.login.data.repository.MemesRepository
import com.example.login.retrofit.RetrofitInstance

class MemesListViewModel(
    private val repo: MemesRepository,
) : BaseViewModel() {
    var sortMemeType: MutableLiveData<String> = MutableLiveData()

    var listData = Pager(PagingConfig(pageSize = 1)) {
        MemesPagingSource(api = RetrofitInstance.api)
    }.flow.cachedIn(viewModelScope)

    init {
        sortMemeType.value = SortType.WITHOUT.sortName
        sortByType()
    }

    fun sortByType(): MutableList<MemeLocale> {
        val resultList = mutableListOf<MemeLocale>()
        repo.setAvailableMemes().forEach { item ->
            if(item.tags.contains("${sortMemeType.value}")){
                resultList.add(item)
            }
        }
        return resultList
    }
}