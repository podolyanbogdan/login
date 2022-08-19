package com.example.login.data.mapper

import com.example.login.data.localeModels.PictureLocaleModel
import com.example.login.data.models.PictureModel

class PictureMapper : MyMapper<PictureModel, PictureLocaleModel>() {
    override fun map(from: PictureModel): PictureLocaleModel {
        return PictureLocaleModel(
            small = from.small,
            med = from.large,
            large = from.large,
            full = from.full
        )
    }
}