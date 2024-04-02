package com.xavier.newsapp.presentation.search.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.presentation.components.Dimens
import com.xavier.newsapp.presentation.components.NewsTopAppBar
import com.xavier.newsapp.presentation.components.SearchBar
import com.xavier.newsapp.presentation.home.componets.ArticlesList
import com.xavier.newsapp.presentation.search.events.SearchEvent
import com.xavier.newsapp.presentation.search.states.SearchState

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            NewsTopAppBar(
                title = "News"
            )
        }) {
        SearchScreenContent(
            modifier = Modifier.padding(it),
            state = state,
            event = event,
            navigateToDetails = navigateToDetails
        )
    }

}

@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = { event(SearchEvent.SearchNews) }
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
                articles = articles,
                onClick = { article ->
                    navigateToDetails(article)
                }
            )
        }


    }


}
