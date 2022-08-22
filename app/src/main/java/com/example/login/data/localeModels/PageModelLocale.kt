package com.example.login.data.localeModels


data class PageModelLocale(
    val numPages: Int,
    val numRecordings: String,
    val numSpecies: String,
    val page: Int,
    var birdModelsLocale: List<BirdModelLocale>
)