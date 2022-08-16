package com.example.login.data.repository

import com.example.login.data.BirdModel
import com.example.login.data.PageModel
import com.example.login.data.SearchModel
import com.example.login.data.enumss.From
import com.example.login.retrofit.RetrofitInstance
import retrofit2.Response

var birdDetailModel = BirdModel()
var searchModel = SearchModel()
var defRequest = ""
var searchType = From.DEFAULT
class BirdRepository {

    //retrofit request
    suspend fun getDefaultSearch(
        value: String
    ): PageModel {
        return RetrofitInstance.api.searchDefault(value)
    }

    suspend fun getAdvancedSearch(
        value: String
    ): PageModel {
       return RetrofitInstance.api.searchRecordings(value)
    }

    suspend fun getResponseTest(
        value: String
    ): Response<PageModel> {
        return RetrofitInstance.api.reponseTest(value)
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

    fun getSearchRequest(value: SearchModel){
        searchModel = value
    }
    fun setSearchRequest(): SearchModel {
        return searchModel
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