package com.example.login.repo

import com.example.login.retrofit.api.AgeApi
import com.example.login.retrofit.entity.AgeEntity
import retrofit2.Response

class AgeRepositoryImpl(
    private val ageApi: AgeApi
) : AgeRepository {
    override suspend fun getAgeByName(name: String): Response<AgeEntity> {
        return ageApi.getAge(name)
    }
}