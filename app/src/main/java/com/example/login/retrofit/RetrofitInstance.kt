package com.example.login.retrofit

import com.example.login.data.constants.Constants.BASE_URL
import com.example.login.retrofit.api.MemesAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val client = OkHttpClient.Builder()
                .addInterceptor(RemoveCharacterInterceptor())
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: MemesAPI by lazy {
            retrofit.create(MemesAPI::class.java)
        }
    }
}