package com.example.login.retrofit

import android.net.http.HttpResponseCache.install
import com.example.login.data.constants.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkFactory {

    fun <S> createService(protocol: Class<S>): S {
        return retrofit.create(protocol)
    }

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        private val retrofit by lazy {

            val json = Json { ignoreUnknownKeys = true}
            val contentType = "application/json".toMediaType()
            val logging = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .client(client)
                .build()
        }
    }
}