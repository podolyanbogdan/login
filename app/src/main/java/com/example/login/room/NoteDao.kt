package com.example.login.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao{
    @Query("SELECT * FROM noteTbl ORDER BY id DESC")
    fun takeAllNotes(): LiveData<List<NoteModel>>

    @Query("DELETE FROM noteTbl")
    fun clearNotes()

    @Query("DELETE FROM noteTbl WHERE id = :noteId")
    fun deleteByUserId(noteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Update
    fun updateUsers(noteModel: NoteModel)
}