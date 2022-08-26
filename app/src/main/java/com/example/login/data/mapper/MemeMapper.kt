package com.example.login.data.mapper

import com.example.login.data.models.apiModels.MemeAPI
import com.example.login.data.models.localeModels.MemeLocale

class MemeMapper() : MyMapper<MemeAPI, MemeLocale>() {
    override fun map(from: MemeAPI): MemeLocale {
        return MemeLocale(
            bottomText = from.bottomText ?: "",
            iD = from.iD ?: 0,
            image = from.image ?: "",
            name = from.name ?: "",
            tags = from.tags ?: "",
            topText = from.topText ?: ""
        )
    }
}