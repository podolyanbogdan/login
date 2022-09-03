package com.example.login.retrofit.api

import com.example.login.data.constants.Constants.GET_URL
import com.example.login.data.modelsAPI.DailyForecastAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET(GET_URL)
    suspend fun loadForecastWeekly(
        @Query("q",encoded = true)
        city: String,
        @Query("units", encoded = true)
        unitsType: String,
        @Query("appid",encoded = true)
        appid: String,
    ) : Response<DailyForecastAPI>
}