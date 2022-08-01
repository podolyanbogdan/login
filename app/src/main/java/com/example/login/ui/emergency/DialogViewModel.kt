package com.example.login.ui.emergency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.repository.NoteRepository
import org.jetbrains.anko.doAsync

class DialogViewModel(private val repository: NoteRepository): ViewModel() {
    var yesTrigger: MutableLiveData<Boolean> = MutableLiveData()
    var noTrigger: MutableLiveData<Boolean> = MutableLiveData()


    fun yesClick(){
        doAsync {
            repository.clearNotes()
        }
        yesTrigger.value = true
    }
    fun noClick(){
        noTrigger.value = true
    }

}