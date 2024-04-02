package com.xavier.newsapp.presentation.search.events

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}