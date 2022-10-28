package com.example.login.retrofit.api

import com.example.login.retrofit.entity.AgeEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AgeApi {
    @GET("/")
    suspend fun getAge(
        @Query("name")
        name: String
    ): Response<AgeEntity>
}