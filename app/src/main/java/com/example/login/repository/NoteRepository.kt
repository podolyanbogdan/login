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
        db.noteDao().editUser(title, content, color,id)

    fun deleteById(id: Int) = db.noteDao().deleteByUserId(id)

    fun clearNotes() = db.noteDao().clearNotes()

    //sort
    fun sortBy(sortBy: String, isAsc: Int): LiveData<List<NoteModel>> =
        db.noteDao().sortBy(sortBy, isAsc)

    fun requestForSort(sortBy: String){
        req = sortBy
    }
    fun checkRequest():String{
        return req
    }


    //for recycler adapters
    fun setColors(): List<ColorModel> {
        return listOf(
            ColorModel(0,NoteColors.Red.colName, NoteColors.Red.col),
            ColorModel(1,NoteColors.Yellow.colName, NoteColors.Yellow.col),
            ColorModel(2,NoteColors.Green.colName, NoteColors.Green.col),
            ColorModel(3,NoteColors.Pink.colName, NoteColors.Pink.col),
            ColorModel(4,NoteColors.Blue.colName, NoteColors.Blue.col),
            ColorModel(5,NoteColors.LightBlue.colName, NoteColors.LightBlue.col),
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