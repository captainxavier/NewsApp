package com.xavier.newsapp.presentation.home.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.presentation.components.Dimens.MediumPadding1
import com.xavier.newsapp.presentation.components.NewsTopAppBar
import com.xavier.newsapp.presentation.components.SearchBar
import com.xavier.newsapp.presentation.home.componets.ArticlesList
import com.xavier.newsapp.presentation.nav_graph.Route

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewsTopAppBar(
                title = "News"
            )
        }) {
        HomeScreenContent(
            modifier = Modifier.padding(it),
            articles = articles,
            navigateToSearch = navigateToSearch,
            navigateToDetails = navigateToDetails
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        val titles by remember {
            derivedStateOf {
                if (articles.itemCount > 10) {
                    articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                        .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
                } else {
                    ""
                }
            }
        }

        SearchBar(modifier = Modifier
            .padding(horizontal = MediumPadding1)
            .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToSearch()
            }


        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            }
        )

    }


}
