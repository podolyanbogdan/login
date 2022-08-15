package com.example.login.data.repository

import com.example.login.data.BirdModel
import com.example.login.data.PageModel
import com.example.login.retrofit.RetrofitInstance

var birdDetailModel = BirdModel()
class BirdRepository {

    suspend fun getAdvancedSearch(): PageModel {
       return RetrofitInstance.api.searchRecordings()
    }

    fun getBirdDetail(birdModel: BirdModel){
        birdDetailModel = birdModel
    }
    fun setBirdDetail(): BirdModel {
        return birdDetailModel
    }
}