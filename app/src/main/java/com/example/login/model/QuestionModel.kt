package com.example.login.model

data class QuestionModel(
    val id: Int = 0,
    val questionName: String = "",
    val optionOne: String = "",
    val optionTwo: String = "",
    val optionThree: String = "",
    val optionFour: String = "",
    val correctAnswer: Int = 0,
)