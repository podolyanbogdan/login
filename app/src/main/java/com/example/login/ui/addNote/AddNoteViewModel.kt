package com.example.login.ui.addNote

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import org.jetbrains.anko.doAsync

class AddNoteViewModel(private val repository: NoteRepository) : BaseViewModel() {
    val createNoteTrigger: MutableLiveData<Boolean> = MutableLiveData()

    val titleValue: MutableLiveData<String> = MutableLiveData()
    val contentValue: MutableLiveData<String> = MutableLiveData()


    fun createNote() {
        val title = titleValue.value ?: ""
        val content = contentValue.value ?: ""
        val note = NoteModel(
            title = title,
            content = content,
        )

        doAsync {
            repository.insertNotes(note)
        }

        createNoteTrigger.value = true
    }
}