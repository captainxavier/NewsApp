package com.xavier.newsapp.presentation.home.states

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)
