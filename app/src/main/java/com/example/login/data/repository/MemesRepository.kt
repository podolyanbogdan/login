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

    //memes id to sort
    val catsMemes = mutableListOf(1, 12, 38, 56)
    val humanMemes =
        mutableListOf(2, 7, 9, 13, 14, 18, 21, 22, 23, 24, 25, 29, 32, 33, 39, 40, 44, 45)
    val cartoonMemes = mutableListOf(5, 6, 20, 35, 43, 49, 65)
    val charactersMemes = mutableListOf(3, 5, 6, 10, 34, 35, 36, 43, 48, 49, 57, 58)
    val anotherMemes = mutableListOf(4, 8, 11, 15, 16, 26, 28, 30, 31, 37, 63)
}