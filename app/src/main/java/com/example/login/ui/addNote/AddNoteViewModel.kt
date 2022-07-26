package com.example.login.ui.addNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.ColorModel
import com.example.login.repository.NoteRepository
import com.example.login.room.NoteModel
import com.example.login.utils.AppUtils.Companion.getCurrentDate
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync


class AddNoteViewModel(private val repository: NoteRepository) : BaseViewModel() {
    val createNoteTrigger: MutableLiveData<Boolean> = MutableLiveData()

    val titleValue: MutableLiveData<String> = MutableLiveData()
    val contentValue: MutableLiveData<String> = MutableLiveData()
    var colors: MutableLiveData<List<ColorModel>> = MutableLiveData()
    var checkedValue: MutableLiveData<Boolean> = MutableLiveData()

    init {
        colors.value = repository.setColors()
    }


    fun createNote() {
        val title = titleValue.value ?: ""
        val content = contentValue.value ?: ""
        val color = repository.getColorNote()
        val isEditable = checkedValue.value ?: false
        val date = repository.getDate()

        if (title.isEmpty() || content.isEmpty()) {
            createNoteTrigger.value = false
        } else {
            val note = NoteModel(
                title = title,
                content = content,
                color = color,
                isEditable = isEditable,
                date = date
            )
            doAsync {
                repository.insertNotes(note)
            }
            createNoteTrigger.value = true
        }
    }
}