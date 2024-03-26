package com.xavier.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.xavier.newsapp.data.repositories.news.NewsRepository
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.domain.models.news.Source
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return repository.getNews(sources = sources)
    }
}