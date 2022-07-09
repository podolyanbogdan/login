package com.example.login.ui.gamesScreens.rigthAwayScene

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.*
import com.example.login.repository.ScenarioRepository

class SceneRightAwayModel(context: Context): BaseViewModel() {
    private val scenario = ScenarioRepository(context)
    private var current = 0
    val displayText: MutableLiveData<String> = MutableLiveData()
    val displayCharacter: MutableLiveData<String> = MutableLiveData()
    private var text: String = scenario.getSceneRightAway(current)
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
        if (current != scenario.sceneRightAway.size) {
            text = scenario.getSceneRightAway(current)
            val resultText = text.replace("1", "").replace("2", "")
            when{
                text[0] == SYLVIE_SAY -> {
                    showCharacter.value = true
                    displayCharacter.value = SYLVIE
                }
                text[0] == ME_SAY -> {
                    showCharacter.value = true
                    displayCharacter.value = ME
                }
                else -> displayCharacter.value = EMPTY
            }
            displayText.value = resultText
            current++
        }
        if (current == scenario.sceneRightAway.size) {
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