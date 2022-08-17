package com.example.login.data.repository

import com.example.login.data.models.BirdModel
import com.example.login.data.models.PageModel
import com.example.login.data.enumss.From
import com.example.login.retrofit.RetrofitInstance

var birdDetailModel = BirdModel()
var stringRequest = ""
var defRequest = ""
var searchType = From.DEFAULT
class BirdRepository {

    //retrofit request
    suspend fun searchBird(
        value: String
    ): PageModel {
       return RetrofitInstance.api.searchBird(value)
    }
    //retrofit request


    // what kind of search retrofit need to do
    fun getSearchType(type: From){
        searchType = type
    }

    fun setSearchType(): From {
        return searchType
    }
    // what kind of search retrofit need to do


    //set search request
    fun getDefaultRequest(value: String){
        defRequest = value
    }

    fun setDefaultRequest(): String {
        return defRequest
    }

    fun getAdvancedRequest(value: String){
        stringRequest = value
    }
    fun setAdvancedRequest(): String {
        return stringRequest
    }
    //set search request


    //for recycler
    fun getBirdDetail(birdModel: BirdModel){
        birdDetailModel = birdModel
    }
    fun setBirdDetail(): BirdModel {
        return birdDetailModel
    }
    //for recycler
}