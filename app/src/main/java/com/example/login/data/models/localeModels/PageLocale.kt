package com.example.login.data.models.localeModels


data class PageLocale(
    val code: Int,
    val data: List<MemeLocale>,
    val message: String,
    val next: String
)