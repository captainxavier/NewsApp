package com.xavier.newsapp.domain.usecases.news

import com.xavier.newsapp.data.local.dao.NewsDao
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.data.repositories.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectArticles @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke():Flow<List<ArticleEntity>>{
        return repository.getArticles()
    }
}