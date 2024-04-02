package com.xavier.newsapp.presentation.bookmark.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.presentation.bookmark.states.BookmarkState
import com.xavier.newsapp.presentation.components.Dimens
import com.xavier.newsapp.presentation.components.NewsTopAppBar
import com.xavier.newsapp.presentation.home.componets.ArticlesList
import com.xavier.newsapp.presentation.home.componets.EmptyScreen
import com.xavier.newsapp.presentation.nav_graph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            NewsTopAppBar(
                title = "Bookmark"
            )
        }) {
        BookmarkScreenContent(
            modifier = Modifier.padding(it),
            state = state,
            navigateToDetails=navigateToDetails
        )
    }

}

@Composable
fun BookmarkScreenContent(
    modifier: Modifier,
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    if (state.articles.isEmpty()){
        EmptyScreen()
    }

    ArticlesList(modifier = modifier.padding(horizontal = Dimens.MediumPadding3),
        articles = state.articles,
        onClick = {
            navigateToDetails(it)
        }
    )

}
