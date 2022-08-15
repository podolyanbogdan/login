package com.example.login.ui.screens.defaultt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.BirdModel
import com.example.login.data.PageModel
import com.example.login.data.repository.BirdRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class DefaultViewModel(
    private val repo: BirdRepository
): BaseViewModel() {
    val onSearchTrigger = SingleLiveEvent<Unit>()
    val goAdvancedTrigger = SingleLiveEvent<Unit>()



    fun onSearch(){
        onSearchTrigger.value = Unit
    }

    fun goAdvanced(){
        goAdvancedTrigger.value = Unit
    }
}