package com.example.login.retrofit.networkLoader

import com.example.login.arch.mapper.Either
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.retrofit.api.NetworkApi
const val UNITS_TYPE = "metric"
const val API_KEY = "e97b416a36ea1fc05113050fd213649b"
class NetworkLoader(
    private val api: NetworkApi
) {
    suspend fun searchByCityName(city : String): Either<DailyForecastAPI> {
        return try {
            val response = api.loadForecastWeekly(city, UNITS_TYPE ,API_KEY)
            val body = response.body()

            if (!response.isSuccessful || body == null) {
                Either.failure(Throwable("Request error"))
            } else {
                Either.success(body)
            }
        } catch (e: Error) {
            Either.failure(e)
        }
    }
}