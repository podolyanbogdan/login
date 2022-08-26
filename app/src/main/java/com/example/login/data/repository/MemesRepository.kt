package com.example.login.data.repository

import com.example.login.data.models.localeModels.MemeLocale

var availableMemes = emptyList<MemeLocale>()

class MemesRepository {

    fun getAvailableMemes(list: List<MemeLocale>) {
        availableMemes = list
    }

    fun setAvailableMemes(): List<MemeLocale> {
        return availableMemes
    }
}