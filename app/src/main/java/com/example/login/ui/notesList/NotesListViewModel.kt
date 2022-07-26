package com.example.login.ui.notesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import org.jetbrains.anko.doAsync

class NotesListViewModel(private val repository: NoteRepository) : BaseViewModel(){
    val fabAddNote: MutableLiveData<Boolean> = MutableLiveData()
    var notes: LiveData<List<NoteModel>> = repository.getAllNotes()


    fun addNote(){
        fabAddNote.value = true
    }

    fun asc(){
        doAsync {
            notes = repository.sortBy("content", 1)
        }
    }
    fun desc(){
       doAsync {
           notes  = repository.sortBy("content", 2)
       }
    }

}