package com.example.login.ui.screens.birdsList.adapter

import com.example.login.data.localeModels.BirdModelLocale
import com.example.login.data.models.BirdModel

interface RecyclerActions {
    fun showMore(birdModel: BirdModelLocale)
}