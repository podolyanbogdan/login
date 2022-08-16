package com.example.login.api

import com.example.login.data.PageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdAPI {

    // default search
    @GET("/api/2/recordings?")
    suspend fun searchDefault(
        @Query("query", encoded = true)
        country: String,
    ) : PageModel


    //advanced
    @GET("/api/2/recordings?")
    suspend fun searchRecordings(
        @Query("query", encoded = true)
        genus: String,
    ) : PageModel

    //test
    @GET("/api/2/recordings?")
    suspend fun reponseTest(
        @Query("query", encoded = true)
        test: String,
    ) : Response<PageModel>
}