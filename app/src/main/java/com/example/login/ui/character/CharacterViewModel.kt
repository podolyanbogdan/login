package com.example.login.ui.character

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.MyRepository


class CharacterViewModel(private val repo: MyRepository) : BaseViewModel() {
    val prevClick: MutableLiveData<Boolean> = MutableLiveData()
    val nextClick: MutableLiveData<Boolean> = MutableLiveData()
    var radioValue = MutableLiveData<Int>()
    var resultRadioId = MutableLiveData<Int>()
    var resultRadioName = MutableLiveData<String>()


    init {
        radioValue.value = 0
    }

    fun prevClicked() {
        prevClick.value = true
    }

    fun nextClicked() {
        if (radioValue.value == 0) {
            nextClick.value = false
        } else {
            repo.saveCharacterScreenState(true)
            resultRadioId.value?.let { repo.saveCharacterId(it) }
            resultRadioName.value?.let { repo.saveCharacterName(it) }
            nextClick.value = true
        }
    }
}
