package com.example.login.ui.gamesScreens.badEndingScene

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.ScenarioRepository

class SceneLaterBadEndingViewModel(context: Context): BaseViewModel() {
    private val scenario = ScenarioRepository(context)
    private var current = 1
    val displayText: MutableLiveData<String> = MutableLiveData()
    private var text: String = scenario.getSceneBadEnding(0)
    var returnInToMenu : MutableLiveData<Boolean> = MutableLiveData()
    var exit : MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()


    init {
        displayText.value = text
    }

    fun nextText() {
        if (current != scenario.sceneBadEnding.size) {
            text = scenario.getSceneBadEnding(current)
            displayText.value = text
            current++
        }
        if(current == scenario.sceneBadEnding.size){
            showOptions.value = true
        }
    }

    fun firstOptionClick() {
        returnInToMenu.value = true
    }

    fun secondOptionClick() {
        exit.value = true
    }
}