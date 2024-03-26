package com.xavier.newsapp.data.repositories.news

import androidx.paging.PagingData
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources:List<String>):Flow<PagingData<Article>>
}