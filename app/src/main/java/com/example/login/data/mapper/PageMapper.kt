package com.example.login.data.mapper

import com.example.login.data.models.apiModels.PageAPI
import com.example.login.data.models.localeModels.PageLocale

class PageMapper(
    private val memeMapper: MemeMapper
) : MyMapper<PageAPI, PageLocale>() {
    override fun map(from: PageAPI): PageLocale {
        return PageLocale(
            code = from.code,
            data = from.data.map { memeMapper.map(it) },
            message = from.message,
            next = from.next
        )
    }
}