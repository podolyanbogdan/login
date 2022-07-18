package com.example.login.ui.points

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.constants.Constants.CHARACTER_NAME_KEY
import com.example.login.constants.Constants.DATE
import com.example.login.constants.Constants.LEVEL_KEY
import com.example.login.constants.Constants.TIME
import com.example.login.data.PointsModel
import com.example.login.repository.PreferenceStorage
import com.example.login.utils.AppUtils.Companion.getCurrentDate

class PointsViewModel(private val prefs: PreferenceStorage) : BaseViewModel() {
    val points: MutableLiveData<List<PointsModel>> = MutableLiveData()


    init {
        updateRecycleData()
    }

    fun getPlayerData(): PointsModel {
        val character = prefs.getCharacterName(CHARACTER_NAME_KEY)
        val level = prefs.getLevel(LEVEL_KEY).toInt().toString()
        return PointsModel(
            character = character.toString(),
            level = level,
            availableDate = getCurrentDate(DATE).toString(),
            availableTime = getCurrentDate(TIME).toString()
        )
    }


    private fun updateRecycleData() {
        val data = listOf(
            getPlayerData(),
            PointsModel(
                character = "Fake Dude ",
                level = "level Master",
                availableDate = "22.05.2021",
                availableTime = "16:50",
            ),
            PointsModel(
                character = "Fake Dude 2 ",
                level = "level Noob",
                availableDate = "05.10.2022",
                availableTime = "22:15",
            )
        )
        points.value = data
    }
}