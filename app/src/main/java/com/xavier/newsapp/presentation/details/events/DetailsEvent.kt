package com.xavier.newsapp.presentation.details.events

import com.xavier.newsapp.data.local.entities.ArticleEntity

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: ArticleEntity) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()
}