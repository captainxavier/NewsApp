package com.xavier.newsapp.data.repositories.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xavier.newsapp.data.local.dao.NewsDao
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.data.remote.news.NewsPagingSources
import com.xavier.newsapp.data.remote.news.SearchNewsPagingSources
import com.xavier.newsapp.data.remote.news.api.NewsApi
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSources(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )

            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSources(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )

            }
        ).flow
    }

    override suspend fun upsert(article: ArticleEntity) {
        newsDao.upsert(article)
    }

    override suspend fun delete(article: ArticleEntity) {
        newsDao.delete(article)
    }

    override fun getArticles(): Flow<List<ArticleEntity>> {
        return newsDao.getArticles()
    }

    override suspend fun getArticle(url: String): ArticleEntity? {
        return newsDao.getArticle(url = url)
    }
}