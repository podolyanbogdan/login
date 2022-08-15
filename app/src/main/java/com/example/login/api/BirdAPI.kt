package com.example.login.api

import com.example.login.data.PageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BirdAPI {
    @GET("/api/2/recordings?")
    suspend fun searchRecordings(
        @Query("query")
        query : String = "cnt:brazil"
    ) : PageModel
}