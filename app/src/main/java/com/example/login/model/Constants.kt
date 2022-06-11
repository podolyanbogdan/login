package com.example.login.model

object Constants {

    fun getQuestions(): ArrayList<QuestionModel>{
        val questionsList = ArrayList<QuestionModel>()
        val question1 = QuestionModel(
            1,
            "Какова продолжительность жизни стрекозы?",
            "24 часа",
            "3 дня",
            "2 часа",
            "Неделя",
            1
        )
        questionsList.add(question1)
        val question2 = QuestionModel(
            2,
            "Ваша машина двигается со скоросью 200 км/час.\n Впереди столб \n Ваши действия?",
            "Выпрыгнуть из машины",
            "Нажать на газ",
            "Нажать на тормоз",
            "Смириться",
            3
        )
        questionsList.add(question2)
        val question3 = QuestionModel(
            3,
            "С какой высоты Роналду поразил ворота «Сампдории», забив с головы?",
            "2,5 метра",
            "2 метра",
            "5 метров",
            "60 сантиметров",
            1
        )
        questionsList.add(question3)
        val question4 = QuestionModel(
            4,
            "Самая высокая точка в мире?",
            "Лхоцзе",
            "Канченджанга",
            "Чогори",
            "Джомолунгма (Эверест)",
            4
        )
        questionsList.add(question4)

        val question5 = QuestionModel(
            4,
            "В какой галактике Мы живем?",
            "Андромеда",
            "Вечный Путь",
            "Млечный Путь",
            "Украина",
            3
        )
        questionsList.add(question5)

        val question6 = QuestionModel(
            4,
            "Самая высокая точка в мире?",
            "13 Октября 2012",
            "22 Июля 2011",
            "31 Сентрября 2009",
            "25 Мая 2012",
            2
        )
        questionsList.add(question6)

        val question7 = QuestionModel(
            4,
            "Гугол это?",
            "единица со 100 нулями",
            "Поисковик",
            "двойка со 100 нулями",
            "единица со 10 нулями",
            4
        )
        questionsList.add(question7)

        return questionsList
    }

    fun getQuestionsEditText(): ArrayList<QuestionEditTextModel>{
        val questionsListEditText = ArrayList<QuestionEditTextModel>()

        val questionEditText1 = QuestionEditTextModel(
            1,
            "Сколько раз дикаприо был номинирован на Оскар?",
            6
        )
        questionsListEditText.add(questionEditText1)

        val questionEditText2 = QuestionEditTextModel(
            2,
            "Корень из 144",
            12
        )
        questionsListEditText.add(questionEditText2)

        val questionEditText3 = QuestionEditTextModel(
            3,
            "Сколько фильмов снял Тарантино?",
            9
        )
        questionsListEditText.add(questionEditText3)

        val questionEditText4 = QuestionEditTextModel(
            3,
            "Сколько костей у взрослого человека?",
            206
        )
        questionsListEditText.add(questionEditText4)

        val questionEditText5 = QuestionEditTextModel(
            3,
            "Какой сейчас год",
            2022
        )
        questionsListEditText.add(questionEditText5)

        val questionEditText6 = QuestionEditTextModel(
            3,
            "Сколько планет в нашей солнечной системе?",
            8
        )
        questionsListEditText.add(questionEditText6)

        val questionEditText7 = QuestionEditTextModel(
            3,
            "В каком году расспался СССР",
             1991
        )
        questionsListEditText.add(questionEditText7)

        val questionEditText8 = QuestionEditTextModel(
            3,
            "В каком году украина стала независимой",
            1991
        )
        questionsListEditText.add(questionEditText8)

        return questionsListEditText
    }
}