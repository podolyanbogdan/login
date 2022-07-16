package com.example.login.data

data class TaskModel(
    val title: String = "",
    val date: String? = "",
    val timeFirst: String? = "",
    val timeBoth: String = "",
    val description: String = "",
    val type: String = "",
    val tags: String = ""
)
