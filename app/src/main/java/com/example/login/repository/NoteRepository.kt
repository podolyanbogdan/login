package com.example.login.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.data.ColorModel
import com.example.login.data.NoteColors
import com.example.login.room.NoteModel
import com.example.login.room.RoomSingleton
import com.example.login.utils.AppUtils

var col = 0

class NoteRepository(application: Application) {

    // room
    private val db: RoomSingleton = RoomSingleton.getInstance(application)

    fun getAllNotes(): LiveData<List<NoteModel>> = db.noteDao().takeAllNotes()

    fun insertNotes(noteModel: NoteModel) = db.noteDao().insertNote(noteModel)

    fun updateNotes(id: Int, title: String, content: String, color: Int) =
        db.noteDao().editUser(id, title, content, color)

    fun deleteById(id: Int) = db.noteDao().deleteByUserId(id)

    fun clearNotes() = db.noteDao().clearNotes()

    //sort
    fun sortBy(sortBy: String, isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortBy(sortBy, isAsc)


    //for recycler adapters
    fun setColors(): List<ColorModel> {
        return listOf(
            ColorModel(NoteColors.Red.colName, NoteColors.Red.col),
            ColorModel(NoteColors.Yellow.colName, NoteColors.Yellow.col),
            ColorModel(NoteColors.Green.colName, NoteColors.Green.col),
            ColorModel(NoteColors.Pink.colName, NoteColors.Pink.col),
            ColorModel(NoteColors.Blue.colName, NoteColors.Blue.col),
            ColorModel(NoteColors.LightBlue.colName, NoteColors.LightBlue.col),
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