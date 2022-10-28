package com.example.login.repo

import com.example.login.retrofit.entity.AgeEntity
import retrofit2.Response

interface AgeRepository {
   suspend fun getAgeByName(name: String): Response<AgeEntity>
}