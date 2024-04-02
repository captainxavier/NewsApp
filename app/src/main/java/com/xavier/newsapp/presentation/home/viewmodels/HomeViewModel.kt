package com.xavier.newsapp.presentation.home.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.xavier.newsapp.domain.usecases.news.GetNewsUseCase
import com.xavier.newsapp.presentation.home.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
):ViewModel() {
    var state = mutableStateOf(HomeState())
        private set

    val news = newsUseCase.getNews(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)

}