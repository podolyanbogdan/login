package com.example.login.ui.points

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.UserModel
import com.example.login.json.JsonConverter
import com.example.login.repository.PreferenceStorage

class PointsViewModel(private val prefs: PreferenceStorage) : BaseViewModel() {
    val points: MutableLiveData<MutableList<UserModel>> = MutableLiveData()


    fun getPlayerData() {
        val userModel = JsonConverter(prefs).gson.fromJson(
            JsonConverter(prefs).jsonString,
            UserModel::class.java
        )
        updateRecycleData(userModel)
    }


    private fun updateRecycleData(user: UserModel) {
        val data = mutableListOf(user)
        points.value = data
    }
}