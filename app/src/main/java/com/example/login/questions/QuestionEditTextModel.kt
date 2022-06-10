package com.example.login.questions

data class QuestionEditTextModel(
    val questEditList: MutableMap<String, Any> = mutableMapOf(
        "id" to 0,
        "questionName" to "",
        "correctAnswerEditText" to 0
    )
){
}