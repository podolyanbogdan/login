package com.example.login.data.repository

import com.example.login.data.models.BirdModel
import com.example.login.data.models.PageModel
import com.example.login.data.enumss.From
import com.example.login.data.states.result
import com.example.login.retrofit.RetrofitInstance

var birdDetailModel = BirdModel()
var stringRequest = ""
var defRequest = ""
var searchType = From.DEFAULT
class BirdRepository {

    // retrofit request status
    fun getPosts(value: String) = result {
        RetrofitInstance.api.responseBird(value)
    }
    // retrofit request status

    //retrofit request
    suspend fun searchBird(
        value: String
    ): PageModel {
       return RetrofitInstance.api.searchBird(value)
    }
    //retrofit request


    // what kind of search retrofit need to do(advanced or default)
    fun getSearchType(type: From){
        searchType = type
    }

    fun setSearchType(): From {
        return searchType
    }
    // what kind of search retrofit need to do(advanced or default)


    //set search request(advanced or default)
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
    //set search request(advanced or default)


    //for recycler
    fun getBirdDetail(birdModel: BirdModel){
        birdDetailModel = birdModel
    }
    fun setBirdDetail(): BirdModel {
        return birdDetailModel
    }
    //for recycler
}