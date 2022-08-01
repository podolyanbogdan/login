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
        when(sortName.type){
            SortName.Date.type -> {
                notes.addSource(repository.sortByDate(sortType.type)) {
                    notes.value = it
                }
            }

            SortName.Title.type -> {
                notes.addSource(repository.sortByTitle(sortType.type)) {
                    notes.value = it
                }
            }

            SortName.Color.type -> {
                notes.addSource(repository.sortByColor(sortType.type)) {
                    notes.value = it
                }
            }

            SortName.Content.type -> {
                notes.addSource(repository.sortByContent(sortType.type)) {
                    notes.value = it
                }
            }
        }
    }

    init {
        loadCurrencyList()
    }

    fun addNote() {
        fabAddNote.value = true
    }

}

