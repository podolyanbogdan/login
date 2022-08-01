package com.example.login.room

import com.example.login.arch.mapper.Mapper
import com.example.login.data.NoteTempModel

class MyMapper: Mapper<NoteTempModel, NoteModel> {
    override fun tempToNote(from: NoteTempModel): NoteModel {
        return NoteModel(
            title = from.titleTemp,
            content = from.contentTemp,
            color = from.colorTemp,
            date = from.dateTemp,
            isEditable = from.isEditableTemp
        )
    }
}