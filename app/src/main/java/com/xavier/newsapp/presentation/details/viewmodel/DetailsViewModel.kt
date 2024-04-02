package com.xavier.newsapp.presentation.details.viewmodel

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.domain.usecases.news.GetNewsUseCase
import com.xavier.newsapp.presentation.details.events.DetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    var isBookmarked by mutableStateOf(false)
        private set


    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.selectArticle(event.article.url)
                    isBookmarked = if (article == null) {
                        upsertArticle(event.article)
                        false
                    } else {
                        deleteArticle(event.article)
                        true
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

        }
    }

    private fun upsertArticle(article: ArticleEntity) {
        viewModelScope.launch {
            newsUseCase.upsertArticle(entity = article)
            sideEffect = "Article Saved"
        }
    }

    private fun deleteArticle(article: ArticleEntity) {
        viewModelScope.launch {
            newsUseCase.deleteArticle(entity = article)
            sideEffect = "Article Deleted"
        }
    }
}