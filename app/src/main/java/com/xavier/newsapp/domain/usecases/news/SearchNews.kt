package com.xavier.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.xavier.newsapp.data.repositories.news.NewsRepository
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return repository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}