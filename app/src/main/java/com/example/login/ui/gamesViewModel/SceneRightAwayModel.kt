package com.example.login.ui.gamesViewModel

import androidx.lifecycle.MutableLiveData
import com.example.login.ME
import com.example.login.SYLVIE
import com.example.login.arch.BaseViewModel
import com.example.login.repository.ScenarioRepository

class SceneRightAwayModel: BaseViewModel() {
    private var current = 0
    val displayText: MutableLiveData<String> = MutableLiveData()
    val displayCharacter: MutableLiveData<String> = MutableLiveData()
    private var text: String = ScenarioRepository.getSceneRightAway(current)
    var showSilviaa: MutableLiveData<Boolean> = MutableLiveData()
    var showCharacter: MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic : MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic2 : MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic3 : MutableLiveData<Boolean> = MutableLiveData()
    var jumpGame: MutableLiveData<Boolean> = MutableLiveData()
    var jumpBook: MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()
    var changeBackground: MutableLiveData<Boolean> = MutableLiveData()

    init {
        nextText()
    }

    fun nextText() {
        showCharacter.value = false
        if (current == 5) {
            changeBackground.value = true
            showSilviaa.value = false
        }
        if (current == 9) { showSilviaa.value = true }
        if (current == 13) changeSilviaPic.value = true
        if (current == 15) changeSilviaPic2.value = true
        if (current != ScenarioRepository.sceneRightAway.size) {
            text = ScenarioRepository.getSceneRightAway(current)
            val resultText = text.replace("@", "").replace("#", "")
            when{
                text[0] == '@' -> {
                    showCharacter.value = true
                    displayCharacter.value = SYLVIE
                }
                text[0] == '#' -> {
                    showCharacter.value = true
                    displayCharacter.value = ME
                }
                else -> displayCharacter.value = ""
            }
            displayText.value = resultText
            current++
        }
        if (current == ScenarioRepository.sceneRightAway.size) {
            changeSilviaPic3.value = true
            showOptions.value = true
        }
    }

    fun firstOptionClick() {
        jumpGame.value = true
    }
    fun secondOptionClick() {
        jumpBook.value = true
    }
}