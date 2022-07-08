package com.example.login.ui.gamesScreens.gameScene

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.*
import com.example.login.repository.ScenarioRepository

class SceneGameViewModel(context: Context) : BaseViewModel() {
    private val scenario = ScenarioRepository(context)
    private var current = 0
    val displayText: MutableLiveData<String> = MutableLiveData()
    val displayCharacter: MutableLiveData<String> = MutableLiveData()
    private var text: String = scenario.getSceneGame(current)
    private var showSilvia: MutableLiveData<Boolean> = MutableLiveData()
    var showCharacter: MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic: MutableLiveData<Boolean> = MutableLiveData()
    var jumpMarry: MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()

    init {
        nextText()
    }

    fun nextText() {
        showSilvia.value = true
        if (current != scenario.sceneGame.size) {
            text = scenario.getSceneGame(current)
            val resultText = text.replace("1", "").replace("2", "")
            if (current == 7) changeSilviaPic.value = true
            when {
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
        }
        if (current == scenario.sceneGame.size) {
            jumpMarry.value = true
        }
        current++
    }

}