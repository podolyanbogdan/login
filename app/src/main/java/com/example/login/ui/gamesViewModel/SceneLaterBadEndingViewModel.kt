package com.example.login.ui.gamesViewModel

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.ScenarioRepository

class SceneLaterBadEndingViewModel: BaseViewModel() {
    private var current = 1
    val displayText: MutableLiveData<String> = MutableLiveData()
    private var text: String = ScenarioRepository.getSceneBadEnding(0)
    var returnInToMenu : MutableLiveData<Boolean> = MutableLiveData()
    var exit : MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()


    init {
        displayText.value = text
    }

    fun nextText() {
        if (current != ScenarioRepository.sceneBadEnding.size) {
            text = ScenarioRepository.getSceneBadEnding(current)
            displayText.value = text
            current++
        }
        if(current == ScenarioRepository.sceneBadEnding.size){
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