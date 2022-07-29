package com.example.login.data

data class NoteTempModel(
    val titleTemp: String = "",
    val contentTemp: String = "",
    val colorTemp: Int = 0,
    val dateTemp: String = "",
    val isEditableTemp: Boolean = false,
    val isExpandedTemp: Boolean = false
)