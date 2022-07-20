package com.example.login.json

import com.example.login.constants.Constants
import com.example.login.data.UserModel
import com.example.login.repository.PreferenceStorage
import com.example.login.utils.AppUtils
import com.google.gson.Gson

class JsonConverter(prefs: PreferenceStorage) {
    var gson = Gson()

    var jsonString: String = gson.toJson(
        UserModel(
            location = "65.45455",
            level = prefs.getLevel(Constants.LEVEL_KEY).toInt().toString(),
            character = prefs.getCharacterName(Constants.CHARACTER_NAME_KEY).toString(),
            availableDate = AppUtils.getCurrentDate(Constants.DATE).toString(),
            availableTime = AppUtils.getCurrentDate(Constants.TIME).toString()
        )
    )
}
