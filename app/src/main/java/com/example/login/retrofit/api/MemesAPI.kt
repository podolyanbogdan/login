package com.example.login.retrofit.api

import com.example.login.data.constants.Constants.GET_URL
import com.example.login.data.models.apiModels.PageAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryName

interface MemesAPI {
    @GET(GET_URL)
    suspend fun getMemes(
        @QueryName(encoded = true)
        pageNum: Int
    ) : Response<PageAPI>
}