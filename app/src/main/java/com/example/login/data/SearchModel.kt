package com.example.login.data

data class SearchModel(
    var search: String = "",
    var gen: String = "",
    var type: String = "",
    var subtype: String = "",
    var soundType: String = "",
    var place: String = "",
    var country: String = "Without Country",
    var remarks: String = "",
    var author: String = ""
)
