package com.example.login.ui.editNote

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.ColorModel
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import org.jetbrains.anko.doAsync

class EditNoteViewModel(private val repository: NoteRepository) : BaseViewModel() {
    var colors: MutableLiveData<List<ColorModel>> = MutableLiveData()

    val titleValue: MutableLiveData<String> = MutableLiveData()
    val contentValue: MutableLiveData<String> = MutableLiveData()
    var editNoteTrigger: MutableLiveData<Boolean> = MutableLiveData()

    var idToCheck: MutableLiveData<Int> = MutableLiveData()

    init {
        colors.value = repository.setColors()

    }

    fun createNote() {
        val title = titleValue.value ?: ""
        val content = contentValue.value ?: ""
        val color = repository.getColorNote()

        if (title.isEmpty() || content.isEmpty()) {
            editNoteTrigger.value = false
        } else {
            doAsync {
                idToCheck.value?.let { repository.updateNotes(it, title, content, color) }
            }
            editNoteTrigger.value = true
        }
    }
}