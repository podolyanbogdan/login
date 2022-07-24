package com.example.login.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.login.room.NoteModel
import com.example.login.room.NoteDao
import com.example.login.room.RoomSingleton

class NoteRepository(application: Application) {
    private val db:RoomSingleton = RoomSingleton.getInstance(application)

    fun getAllNotes(): LiveData<List<NoteModel>> = db.noteDao().takeAllNotes()

     fun insertNotes(noteModel: NoteModel) = db.noteDao().insertNote(noteModel)

     fun updateNotes(noteModel: NoteModel) = db.noteDao().updateUsers(noteModel)

     fun deleteById(id: Int) = db.noteDao().deleteByUserId(id)

     fun clearNotes() = db.noteDao().clearNotes()

}