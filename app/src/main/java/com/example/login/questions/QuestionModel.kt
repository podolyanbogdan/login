package com.example.login.questions

data class QuestionModel(
    val questMainList: MutableMap<String, Any> = mutableMapOf(
        "id" to 0,
        "questionName" to "",
        "optionOne" to "",
        "optionTwo" to "",
        "optionThree" to "",
        "optionFour" to "",
        "correctAnswer" to 0
    )
)