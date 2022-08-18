package com.example.login.data.states

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


fun <T> result(call:suspend() -> Response<T>): Flow<NetworkResult<T?>> = flow {
    emit(NetworkResult.Loading)

    try {

        val c = call()
        c.let {
            if(c.isSuccessful){
                emit(NetworkResult.Success(it.body()))
            } else {
                c.errorBody()?.let { error ->
                    error.close()
                    emit(NetworkResult.Failure(error.toString()))
                }
            }
        }

    }catch (t: Throwable){
        t.printStackTrace()
        emit(NetworkResult.Failure(t.message.toString()))
    }
}
