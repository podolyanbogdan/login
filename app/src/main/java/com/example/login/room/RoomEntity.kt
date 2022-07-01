package com.example.login.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordTbl")
data class Word(
    @PrimaryKey
    var id:Long?,
    @ColumnInfo(name = "word")
    var name: String,
    @ColumnInfo(name = "wordTime")
    var wordTime: String
)
