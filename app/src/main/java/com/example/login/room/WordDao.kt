package com.example.login.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao{
    @Query("SELECT * FROM wordTbl ORDER BY id DESC")
    fun allWord(): LiveData<List<Word>>

    @Query("DELETE FROM wordTbl")
    fun deleteHistory()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)
}