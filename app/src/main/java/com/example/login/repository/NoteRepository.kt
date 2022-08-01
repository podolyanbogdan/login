package com.example.login.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.login.data.ColorModel
import com.example.login.data.NoteColors
import com.example.login.room.NoteModel
import com.example.login.room.RoomSingleton
import com.example.login.utils.AppUtils

var col = 0
var req = ""

class NoteRepository(application: Application) {

    // room
    private val db: RoomSingleton = RoomSingleton.getInstance(application)

    fun getAllNotes(): LiveData<List<NoteModel>> = db.noteDao().takeAllNotes()

    fun insertNotes(noteModel: NoteModel) = db.noteDao().insertNote(noteModel)

    fun updateNotes(title: String, content: String, color: Int, id: Int) =
        db.noteDao().editUser(title, content, color, id)

    fun deleteById(id: Int) = db.noteDao().deleteByUserId(id)

    fun clearNotes() = db.noteDao().clearNotes()

    //sort
    fun sortByDate(isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortByDate(isAsc)

    fun sortByTitle(isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortByTitle(isAsc)

    fun sortByColor(isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortByColor(isAsc)

    fun sortByContent(isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortByContent(isAsc)


    //for recycler adapters

    fun setColors(): List<ColorModel> {
        return listOf(
            ColorModel(NoteColors.Red.value),
            ColorModel(NoteColors.Yellow.value),
            ColorModel(NoteColors.Green.value),
            ColorModel(NoteColors.Pink.value),
            ColorModel(NoteColors.Blue.value),
            ColorModel(NoteColors.LightBlue.value),
        )
    }

    fun saveColor(color: Int) {
        col = color
    }

    fun getColorNote(): Int {
        return col
    }

    fun getDate(): String {
        return AppUtils.getCurrentDate()
    }

}