package com.xavier.newsapp.domain.usecases.news

import com.xavier.newsapp.data.local.dao.NewsDao
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.data.repositories.news.NewsRepository
import javax.inject.Inject

class SelectArticle @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(url: String): ArticleEntity? {
        return repository.getArticle(url = url)
    }
}