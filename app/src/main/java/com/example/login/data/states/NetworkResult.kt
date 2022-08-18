package com.example.login.data.states

sealed class NetworkResult<out T> {
    data class Success<out R>(val data:R?): NetworkResult<R>()
    data class Failure(val msg:String): NetworkResult<Nothing>()
    object Loading: NetworkResult<Nothing>()
}