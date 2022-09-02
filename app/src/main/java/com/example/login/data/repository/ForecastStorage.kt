package com.example.login.data.repository

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.arch.mapper.Either
import com.example.login.arch.mapper.onFailure
import com.example.login.arch.mapper.onSuccess
import com.example.login.data.localeModels.DailyForecastLocale
import com.example.login.data.mapper.DailyForecastMapper
import com.example.login.data.modelsAPI.DailyForecastAPI
import com.example.login.retrofit.networkLoader.NetworkLoader
import java.util.*

class ForecastStorage(
    private val networkLoader: NetworkLoader,
    private val prefsRepository: PrefsRepository,
    private val context: Context,
    private val dailyForecastMapper: DailyForecastMapper
) {
    var cityList = mutableListOf(decodeLocation())
    private var currentCity = DailyForecastLocale()
    var errorMessage = ""
    var isStop  = false
    suspend fun getCurrentCity() {
        networkLoader.searchByCityName(decodeLocation()).onSuccess { res ->
            currentCity = dailyForecastMapper.map(res)
        }
    }

    suspend fun getForecastData(): MutableList<DailyForecastLocale> {
        val result = mutableListOf<DailyForecastLocale>()
        cityList.forEachIndexed { index, city ->
            val response = networkLoader.searchByCityName(city)
            when{
                response.isSuccess -> {
                    response.onSuccess { item ->
                        result.add(index, dailyForecastMapper.map(item))
                    }
                }
                response.isFailure -> {
                    response.onFailure { error ->
                        errorMessage = error.localizedMessage as String
                    }
                }
            }
        }
        return result
    }


    private fun decodeLocation(): String {
        val geocoder = Geocoder(context, Locale.US)
        val addresses: List<Address>? = geocoder.getFromLocation(
            Double.fromBits(prefsRepository.getLocationLatitude()),
            Double.fromBits(prefsRepository.getLocationLongitude()),
            1,
        )
        return addresses!![0].locality
    }

    fun addNewCity(city: String){
        cityList.add(city)
    }

    fun getCityInfo(cityInfo: DailyForecastLocale){
        currentCity = cityInfo
    }

    fun setCityInfo(): DailyForecastLocale {
        return currentCity
    }
}