package com.example.login.arch.mapper

interface Mapper<in Model, out DomainModel> {

    fun tempToNote(from: Model): DomainModel
}