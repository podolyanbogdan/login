package com.example.login.ui.gamesViewModel

import androidx.lifecycle.MutableLiveData
import com.example.login.ME
import com.example.login.SYLVIE
import com.example.login.arch.BaseViewModel
import com.example.login.repository.ScenarioRepository

class SceneBookViewModel: BaseViewModel() {
    private var current = 0
    val displayText: MutableLiveData<String> = MutableLiveData()
    val displayCharacter: MutableLiveData<String> = MutableLiveData()
    private var text: String = ScenarioRepository.getSceneBook(current)
    private var showSilvia: MutableLiveData<Boolean> = MutableLiveData()
    var showCharacter: MutableLiveData<Boolean> = MutableLiveData()
    var changeSilviaPic : MutableLiveData<Boolean> = MutableLiveData()
    var jumpMarry: MutableLiveData<Boolean> = MutableLiveData()
    var showOptions: MutableLiveData<Boolean> = MutableLiveData()

    init {
        nextText()
    }

    fun nextText() {
        if (current != ScenarioRepository.sceneBook.size) {
            text = ScenarioRepository.getSceneBook(current)
            val resultText = text.replace("@", "").replace("#", "")
            if(current == 5) changeSilviaPic.value = true
            if(current == 1) showSilvia.value = true
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
        }
        if (current == ScenarioRepository.sceneBook.size) {
            jumpMarry.value = true
        }
        current++
    }
}