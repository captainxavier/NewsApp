package com.xavier.newsapp.presentation.search.states

import androidx.paging.PagingData
import com.xavier.newsapp.domain.models.news.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
