package com.example.login.ui.translated

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.arch.lifecycle.SingleLiveEvent
import com.example.login.data.WordTranslatedModel

class TranslatedWordViewModel: BaseViewModel() {
    val wordTranslated: MutableLiveData<List<WordTranslatedModel>> = MutableLiveData()

    val initEvent = SingleLiveEvent<Boolean>()
    fun back(){
        initEvent.postValue(true)
    }
}