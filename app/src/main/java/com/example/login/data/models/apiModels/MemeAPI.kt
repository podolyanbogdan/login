package com.example.login.data.models.apiModels


import com.google.gson.annotations.SerializedName

data class MemeAPI(
    @SerializedName("bottomText")
    val bottomText: String = "",
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("tags")
    val tags: String = "",
    @SerializedName("topText")
    val topText: String = ""
)