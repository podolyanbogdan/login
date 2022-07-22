package com.example.login.ui.points

import androidx.lifecycle.MutableLiveData
import com.example.login.arch.BaseViewModel
import com.example.login.data.UserModel
import com.example.login.repository.MyRepository

class PointsViewModel(private val repo: MyRepository) : BaseViewModel() {
    val points: MutableLiveData<MutableList<UserModel>> = MutableLiveData()
    private var list = mutableListOf<UserModel>()

    init {
        updateRecycleData(repo.getJsonModel())
    }

    fun getPlayerData() {
        val data = repo.getJsonModel()
        updateRecycleData(data)
        repo.markersQuantity(data)
    }


    private fun updateRecycleData(user: UserModel?) {
        user?.let { list.add(it) }
        points.value = list
    }
}