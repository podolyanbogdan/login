package com.example.login.api

import com.example.login.data.models.PageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdAPI {
    //search
    @GET("/api/2/recordings?")
    suspend fun searchBird(
        @Query("query", encoded = true)
        genus: String,
    ) : Response<PageModel>
}