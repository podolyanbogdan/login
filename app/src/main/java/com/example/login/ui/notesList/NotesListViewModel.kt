package com.example.login.ui.notesList

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import com.example.login.room.SortName
import com.example.login.room.SortType

class NotesListViewModel(private val repository: NoteRepository) : BaseViewModel() {
    val fabAddNote: MutableLiveData<Boolean> = MutableLiveData()
    val notes = MediatorLiveData<List<NoteModel>>()

    fun loadCurrencyList() {
        notes.addSource(repository.getAllNotes()) {
            notes.value = it
        }
    }

    fun sortBy(sortName: SortName, sortType: SortType) {
        notes.addSource(repository.sortBy(sortName.type, sortType.type)) {
            notes.value = it
        }
    }

    init {
        loadCurrencyList()
    }

    fun addNote() {
        fabAddNote.value = true
    }

}

