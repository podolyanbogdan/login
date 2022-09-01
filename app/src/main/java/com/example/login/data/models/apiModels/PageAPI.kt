package com.example.login.data.models.apiModels


import com.google.gson.annotations.SerializedName

data class PageAPI(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: MutableList<MemeAPI>,
    @SerializedName("message")
    val message: String,
    @SerializedName("next")
    val next: String
)