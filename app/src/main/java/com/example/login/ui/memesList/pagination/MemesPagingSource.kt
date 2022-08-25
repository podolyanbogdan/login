package com.example.login.ui.memesList.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.login.data.mapper.MemeMapper
import com.example.login.data.models.apiModels.MemeAPI
import com.example.login.data.models.localeModels.MemeLocale
import com.example.login.retrofit.api.MemesAPI

class MemesPagingSource(
    val api: MemesAPI
) : PagingSource<Int, MemeLocale>() {
    override fun getRefreshKey(state: PagingState<Int, MemeLocale>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemeLocale> {
        return try {
            val currentPage = params.key ?: 1
            val response = api.getMemes(currentPage)
            val responseData = mutableListOf<MemeLocale>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(MemeMapper().map(data))

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}