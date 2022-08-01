package com.example.login.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteTbl ORDER BY id DESC")
    fun takeAllNotes(): LiveData<List<NoteModel>>

    @Query("DELETE FROM noteTbl")
    fun clearNotes()

    @Query("DELETE FROM noteTbl WHERE id = :noteId")
    fun deleteByUserId(noteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Query("UPDATE noteTbl SET title = :title ,content= :content,color= :color WHERE id LIKE :id")
    fun editUser(title: String, content: String, color: Int, id: Int)


    @Query(
        "SELECT * FROM noteTbl ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN date END ASC," +
                "CASE WHEN :isAsc = 2 THEN date END DESC")
    fun sortByDate(isAsc: Int): LiveData<List<NoteModel>>

    @Query(
        "SELECT * FROM noteTbl ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN title END ASC," +
                "CASE WHEN :isAsc = 2 THEN title END DESC")
    fun sortByTitle(isAsc: Int): LiveData<List<NoteModel>>

    @Query(
        "SELECT * FROM noteTbl ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN color END ASC," +
                "CASE WHEN :isAsc = 2 THEN color END DESC")
    fun sortByColor(isAsc: Int): LiveData<List<NoteModel>>

    @Query(
        "SELECT * FROM noteTbl ORDER BY " +
                "CASE WHEN :isAsc = 1 THEN content END ASC," +
                "CASE WHEN :isAsc = 2 THEN content END DESC")
    fun sortByContent(isAsc: Int): LiveData<List<NoteModel>>

}