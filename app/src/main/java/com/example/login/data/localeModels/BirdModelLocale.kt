package com.example.login.data.localeModels


data class BirdModelLocale(
    val id: String = "",
    val gen: String = "",
    val sp: String = "",
    val ssp: String = "",
    val en: String = "",
    val rec: String = "",
    val cnt: String = "",
    val loc: String = "",
    val lat: String? = "",
    val lng: String? = "",
    val alt: String = "",
    val type: String = "",
    val url: String = "",
    val file: String = "",
    val fileName: String = "",
    val sono: PictureLocaleModel = PictureLocaleModel(),
    val lic: String = "",
    val q: String = "",
    val length: String = "",
    val time: String = "",
    val date: String = "",
    val uploaded: String = "",
    val also: List<String> = listOf(),
    val rmk: String = "",
    val birdSeen: String = "",
    val playbackUsed: String = "",
)