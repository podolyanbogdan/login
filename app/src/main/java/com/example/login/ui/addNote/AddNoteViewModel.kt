package com.example.login.ui.addNote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.arch.BaseViewModel
import com.example.login.data.ColorModel
import com.example.login.data.NoteTempModel
import com.example.login.repository.NoteRepository
import com.example.login.room.MyMapper
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

        val temp = NoteTempModel(
            titleTemp = title,
            contentTemp = content,
            colorTemp = repository.getColorNote(),
            isEditableTemp = checkedValue.value ?: false,
            dateTemp = repository.getDate()
        )

        if (title.isEmpty() || content.isEmpty()) {
            createNoteTrigger.value = false
        } else {
            doAsync {
                repository.insertNotes(MyMapper().tempToNote(temp))
            }
            createNoteTrigger.value = true
        }
    }
}