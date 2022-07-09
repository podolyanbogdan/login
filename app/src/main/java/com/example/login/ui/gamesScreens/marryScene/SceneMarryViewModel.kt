package com.example.login.ui.gamesScreens.marryScene

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.*
import com.example.login.repository.ScenarioRepository

class SceneMarryViewModel(context: Context) : BaseViewModel() {
    private val scenario = ScenarioRepository(context)
    private var current = 0
    val displayText: MutableLiveData<String> = MutableLiveData()
    val displayCharacter: MutableLiveData<String> = MutableLiveData()
    private var text: String = scenario.getSceneMarry(current)
    var showSilviaa: MutableLiveData<Boolean> = MutableLiveData()
    var showCharacter: MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic: MutableLiveData<Int> = MutableLiveData()
    var jumpMenu: MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()
    var playAgain : MutableLiveData<Boolean> = MutableLiveData()
    var exit : MutableLiveData<Boolean> = MutableLiveData()
    var changeBackground: MutableLiveData<Int> = MutableLiveData()

    init {
        nextText()
    }

    fun nextText() {
        changePic()
        if (current != scenario.sceneMarry.size) {
            text = scenario.getSceneMarry(current)
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
        if (current == scenario.sceneMarry.size) {
            showOptions.value = true
        }
    }

    fun firstOptionClick() {
        playAgain.value = true
    }
    fun secondOptionClick() {
        exit.value = true
    }

    private fun changePic(){
        if(current == 1) changeBackground.value = 1
        if(current == 5) {
            showSilviaa.value = true
            showCharacter.value = true
        }
        if(current == 7) changeSilviaPic.value = 2
        if(current == 9)  changeSilviaPic.value = 3
        if(current == 11) changeSilviaPic.value = 4
        if(current == 14) changeSilviaPic.value = 5
        if(current == 15) changeSilviaPic.value = 6
        if(current == 19) changeSilviaPic.value = 7
        if(current == 20) {
            showSilviaa.value = false
            changeBackground.value = 2
            changeSilviaPic.value = 6
        }
    }
}