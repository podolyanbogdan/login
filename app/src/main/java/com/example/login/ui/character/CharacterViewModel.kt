package com.example.login.ui.character

import android.widget.RadioGroup
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel


class CharacterViewModel: BaseViewModel() {
    val prevClick: MutableLiveData<Boolean> = MutableLiveData()
    val nextClick: MutableLiveData<Boolean> = MutableLiveData()
    var radioValue = MutableLiveData<Int>()

    init {
        radioValue.value = 0
    }

    fun prevClicked(){
        prevClick.value = true
    }

    fun nextClicked(){
        nextClick.value = true
    }
}
