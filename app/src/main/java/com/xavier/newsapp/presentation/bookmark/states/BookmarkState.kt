package com.xavier.newsapp.presentation.bookmark.states

import com.xavier.newsapp.data.local.entities.ArticleEntity

data class BookmarkState(
    val articles: List<ArticleEntity> = emptyList()
)
