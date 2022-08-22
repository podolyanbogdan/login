package com.example.login.data.models


import com.google.gson.annotations.SerializedName

data class BirdModel(
    @SerializedName("also")
    var also: List<String> = listOf(""),
    @SerializedName("alt")
    var alt: String = "",
    @SerializedName("bird-seen")
    var birdSeen: String = "",
    @SerializedName("cnt")
    var cnt: String = "",
    @SerializedName("date")
    var date: String = "",
    @SerializedName("en")
    var en: String = "",
    @SerializedName("file")
    var file: String = "",
    @SerializedName("file-name")
    var fileName: String = "",
    @SerializedName("gen")
    var gen: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("lat")
    var lat: String = "",
    @SerializedName("length")
    var length: String = "",
    @SerializedName("lic")
    var lic: String = "",
    @SerializedName("lng")
    var lng: String = "",
    @SerializedName("loc")
    var loc: String = "",
    @SerializedName("playback-used")
    var playbackUsed: String = "",
    @SerializedName("q")
    var q: String = "",
    @SerializedName("rec")
    var rec: String = "",
    @SerializedName("rmk")
    var rmk: String = "",
    @SerializedName("sono")
    var pictureModel: PictureModel = PictureModel(),
    @SerializedName("sp")
    var sp: String = "",
    @SerializedName("ssp")
    var ssp: String = "",
    @SerializedName("time")
    var time: String = "",
    @SerializedName("type")
    var type: String = "",
    @SerializedName("uploaded")
    var uploaded: String = "",
    @SerializedName("url")
    var url: String = ""
)