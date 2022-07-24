package com.example.login.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTbl")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "content")
    var content: String = "",
    @ColumnInfo(name = "color")
    var color: Int = 0,
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "isEditable")
    var isEditable: Boolean = false

)