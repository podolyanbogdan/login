package com.example.login.data.models


import com.google.gson.annotations.SerializedName

data class PageModel(
    @SerializedName("numPages")
    val numPages: Int,
    @SerializedName("numRecordings")
    val numRecordings: String,
    @SerializedName("numSpecies")
    val numSpecies: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("recordings")
    var birdModels: List<BirdModel>
)