package com.example.login.ui.emergency

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.NoteRepository
import org.jetbrains.anko.doAsync

class EmergencyViewModel(private val repository: NoteRepository) : BaseViewModel() {
    val trigger: MutableLiveData<Boolean> = MutableLiveData()

    fun deleteNotes(){
        doAsync { repository.clearNotes() }
    }

    fun deleteTrigger() {
        trigger.value = true
    }

}