package com.example.login.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class RemoveCharacterInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.toString()

        val string = path.replace("?", "")

        val newRequest = request.newBuilder()
            .url(string)
            .build()

        return chain.proceed(newRequest)
    }

}