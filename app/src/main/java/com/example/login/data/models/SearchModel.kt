package com.example.login.data.models

import com.example.login.data.constants.Const.NO_COUNTRY

data class SearchModel(
    var search: String = "",
    var gen: String = "",
    var type: String = "",
    var subtype: String = "",
    var soundType: String = "",
    var place: String = "",
    var country: String = NO_COUNTRY,
    var remarks: String = "",
    var author: String = ""
)
