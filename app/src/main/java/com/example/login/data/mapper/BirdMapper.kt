package com.example.login.data.mapper

import com.example.login.data.localeModels.BirdModelLocale
import com.example.login.data.models.BirdModel

class BirdMapper(
    private val pictureMapper: PictureMapper
    ) : MyMapper<BirdModel, BirdModelLocale>() {
    override fun map(from: BirdModel): BirdModelLocale {
        return  BirdModelLocale(
            id = from.id,
            gen = from.gen,
            sp = from.sp,
            ssp = from.ssp,
            en = from.en,
            rec = from.rec,
            cnt = from.cnt,
            loc = from.loc,
            lat = from.lat,
            lng = from.lng,
            alt = from.alt,
            type = from.type,
            url = from.url,
            file = from.file,
            fileName = from.fileName,
            sono = pictureMapper.map(from.pictureModel),
            lic = from.lic,
            q = from.q,
            length = from.length,
            time = from.time,
            date = from.date,
            uploaded = from.uploaded,
            also = from.also,
            rmk = from.rmk,
            birdSeen = from.birdSeen,
            playbackUsed = from.playbackUsed
        )
    }

}