package com.xavier.newsapp.data.repositories.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.xavier.newsapp.data.remote.news.NewsPagingSources
import com.xavier.newsapp.data.remote.news.api.NewsApi
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewRepositoryImp @Inject constructor(
    private val newsApi: NewsApi
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
}