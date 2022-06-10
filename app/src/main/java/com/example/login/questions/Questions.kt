package com.example.login.questions

object Questions {

    fun getQuestions(): ArrayList<QuestionModel> {
        val questionsList = ArrayList<QuestionModel>()
        val question1 = QuestionModel(
            questMainList = mutableMapOf(
                "id" to 1,
                "questionName" to "Какова продолжительность жизни стрекозы",
                "optionOne" to "24 часа",
                "optionTwo" to "3 дня",
                "optionThree" to "2 часа",
                "optionFour" to "неделя",
                "correctAnswer" to 1
            )
        )
        questionsList.add(question1)
        val question2 = QuestionModel(
            questMainList = mutableMapOf(
                "id" to 2,
                "questionName" to "Ваша машина двигается со скоросью 200 км/час.\n" +
                        " Впереди столб \n" +
                        " Ваши действия?",
                "optionOne" to "Выпрыгнуть из машины",
                "optionTwo" to "Нажать на газ",
                "optionThree" to "Нажать на тормоз",
                "optionFour" to "Смириться",
                "correctAnswer" to 3
            )
        )
        questionsList.add(question2)
        val question3 = QuestionModel(
            questMainList = mutableMapOf(
                "id" to 3,
                "questionName" to "С какой высоты Роналду поразил ворота «Сампдории», забив с головы?",
                "optionOne" to "2,5 метра",
                "optionTwo" to "2 метра",
                "optionThree" to "5 метров",
                "optionFour" to "60 сантиметров",
                "correctAnswer" to 1
            )
        )
        questionsList.add(question3)
        val question4 = QuestionModel(
            questMainList = mutableMapOf(
                "id" to 4,
                "questionName" to "Самая высокая точка в мире?",
                "optionOne" to "Лхоцзе",
                "optionTwo" to "Канченджанга",
                "optionThree" to "Чогори",
                "optionFour" to "Джомолунгма (Эверест)",
                "correctAnswer" to 4
            )
        )
        questionsList.add(question4)

        val question5 = QuestionModel(

            questMainList = mutableMapOf(
                "id" to 5,
                "questionName" to "В какой галактике Мы живем?",
                "optionOne" to "Андромеда",
                "optionTwo" to "Вечный Путь",
                "optionThree" to "Млечный Путь",
                "optionFour" to "Украина",
                "correctAnswer" to 3
            )
        )
        questionsList.add(question5)

        val question6 = QuestionModel(

            questMainList = mutableMapOf(
                "id" to 6,
                "questionName" to "Самая высокая точка в мире?",
                "optionOne" to "13 Октября 2012",
                "optionTwo" to "22 Июля 2011",
                "optionThree" to "31 Сентрября 2009",
                "optionFour" to "25 Мая 2012",
                "correctAnswer" to 2
            )
        )
        questionsList.add(question6)

        val question7 = QuestionModel(
            questMainList = mutableMapOf(
                "id" to 7,
                "questionName" to "Гугол это?",
                "optionOne" to "единица со 100 нулями",
                "optionTwo" to "Поисковик",
                "optionThree" to "двойка со 100 нулями",
                "optionFour" to "единица со 10 нулями",
                "correctAnswer" to 4
            )
        )
        questionsList.add(question7)

        return questionsList
    }

    fun getQuestionsEditText(): ArrayList<QuestionEditTextModel> {
        val questionsListEditText = ArrayList<QuestionEditTextModel>()

        val questionEditText1 = QuestionEditTextModel(
            questEditList = mutableMapOf(
                "id" to 1,
                "questionName" to "Сколько будет 2 + 2",
                "correctAnswerEditText" to 4
            )
        )
        questionsListEditText.add(questionEditText1)

        val questionEditText2 = QuestionEditTextModel(
            questEditList = mutableMapOf(
                "id" to 2,
                "questionName" to "Корень из 144",
                "correctAnswerEditText" to 12
            )
        )
        questionsListEditText.add(questionEditText2)

        val questionEditText3 = QuestionEditTextModel(
            questEditList = mutableMapOf(
                "id" to 3,
                "questionName" to "ССколько фильмов снял Тарантино?",
                "correctAnswerEditText" to 9
            )
        )
        questionsListEditText.add(questionEditText3)

        val questionEditText4 = QuestionEditTextModel(
            questEditList = mutableMapOf(
                "id" to 4,
                "questionName" to "Сколько костей у взрослого человека?",
                "correctAnswerEditText" to 206
            )
        )
        questionsListEditText.add(questionEditText4)

        val questionEditText5 = QuestionEditTextModel(
            questEditList = mutableMapOf(
                "id" to 5,
                "questionName" to "Какой сейчас год",
                "correctAnswerEditText" to 2022
            )
        )
        questionsListEditText.add(questionEditText5)

        val questionEditText6 = QuestionEditTextModel(

            questEditList = mutableMapOf(
                "id" to 6,
                "questionName" to "Сколько планет в нашей солнечной системе?",
                "correctAnswerEditText" to 8
            )
        )
        questionsListEditText.add(questionEditText6)

        val questionEditText7 = QuestionEditTextModel(

            questEditList = mutableMapOf(
                "id" to 7,
                "questionName" to "В каком году расспался СССР",
                "correctAnswerEditText" to 1991
            )
        )
        questionsListEditText.add(questionEditText7)

        val questionEditText8 = QuestionEditTextModel(

            questEditList = mutableMapOf(
                "id" to 8,
                "questionName" to "В каком году украина стала независимой",
                "correctAnswerEditText" to 1991
            )
        )
        questionsListEditText.add(questionEditText8)

        return questionsListEditText
    }
}