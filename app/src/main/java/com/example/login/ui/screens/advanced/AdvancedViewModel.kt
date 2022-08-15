package com.example.login.ui.screens.advanced

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.BirdModel
import com.example.login.data.PageModel
import com.example.login.data.PictureModel
import com.example.login.data.repository.BirdRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AdvancedViewModel(
    private val repo: BirdRepository
) : BaseViewModel() {
    val onSearchTrigger = SingleLiveEvent<Unit>()

    fun onSearch() {
        onSearchTrigger.value = Unit
    }
}