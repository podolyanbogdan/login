package com.example.login.data.mapper

abstract class MyMapper<in T, out E> {
    abstract fun map (from : T) : E
    open fun map (from : List<T>) = from.map{ map(it) }
}