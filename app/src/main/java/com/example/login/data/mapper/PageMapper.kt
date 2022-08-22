package com.example.login.data.mapper

import com.example.login.data.localeModels.PageModelLocale
import com.example.login.data.models.PageModel

class PageMapper(
    private val birdMapper: BirdMapper
    ) : MyMapper<PageModel, PageModelLocale>() {
    override fun map(from: PageModel): PageModelLocale {
        return PageModelLocale(
            numRecordings = from.numRecordings,
            numSpecies = from.numSpecies,
            page = from.page,
            numPages = from.numPages,
            birdModelsLocale = from.birdModels.map { birdMapper.map(it) }
        )
    }

}