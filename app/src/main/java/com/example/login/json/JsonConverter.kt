package com.example.login.json

import android.content.Context
import com.example.login.constants.Constants
import com.example.login.data.UserModel
import com.example.login.repository.MyRepository
import com.example.login.utils.AppUtils
import com.google.gson.Gson

class JsonConverter(context: Context) {
    private val repo = MyRepository(context)
    var gson = Gson()
    var jsonString: String = gson.toJson(
        UserModel(
            location = repo.getLocation(),
            level = repo.getLevel(),
            character = repo.getCharacter(),
            characterImage = repo.getCharacterPicture(),
            availableDate = repo.getDate(),
            availableTime = repo.getTime()
        )
    )

}
