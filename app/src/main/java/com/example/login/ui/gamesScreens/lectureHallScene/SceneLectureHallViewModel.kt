package com.example.login.ui.gamesScreens.lectureHallScene

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.ScenarioRepository


class SceneLectureHallViewModel(context: Context) : BaseViewModel() {
    private val scenario = ScenarioRepository(context)
    private var current = 1
    val displayText: MutableLiveData<String> = MutableLiveData()
    private var text: String = scenario.getSceneLectureHall(0)
    var changeBackground : MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()
    var jumpSceneLater: MutableLiveData<Boolean> = MutableLiveData()
    var jumpRightAway: MutableLiveData<Boolean> = MutableLiveData()
    var showSilviaa: MutableLiveData<Boolean> = MutableLiveData()


    init {
        displayText.value = text
    }

    fun nextText() {
        if(current == 4) changeBackground.value = true
        if(current == 5) showSilviaa.value = true
        if (current != scenario.sceneLectureHall.size) {
            text = scenario.getSceneLectureHall(current)
            displayText.value = text
            current++
        }
        if(current == scenario.sceneLectureHall.size){
            showOptions.value = true
        }
    }

    fun firstOptionClick() {
        jumpRightAway.value = true
    }
    fun secondOptionClick() {
        jumpSceneLater.value = true
    }
}