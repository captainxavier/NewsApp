package com.xavier.newsapp.data.repositories.news

import androidx.paging.PagingData
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    suspend fun upsert(article: ArticleEntity)

    suspend fun delete(article: ArticleEntity)

    fun getArticles(): Flow<List<ArticleEntity>>

    suspend fun getArticle(url:String):ArticleEntity?
}