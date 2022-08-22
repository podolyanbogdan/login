package com.example.login.data.repository

import com.example.login.data.localeModels.BirdModelLocale
import com.example.login.retrofit.RetrofitInstance

var birdDetailModel = BirdModelLocale()
class BirdRepository {

    suspend fun getBirdResponse(value: String) =
        RetrofitInstance.api.searchBird(value)


    //for recycler
    fun getBirdDetail(birdModel: BirdModelLocale){
        birdDetailModel = birdModel
    }
    fun setBirdDetail(): BirdModelLocale {
        return birdDetailModel
    }
    //for recycler
}