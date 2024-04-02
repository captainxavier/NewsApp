package com.xavier.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.xavier.newsapp.R
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.presentation.bookmark.screens.BookmarkScreen
import com.xavier.newsapp.presentation.bookmark.viewmodels.BookmarkViewModel
import com.xavier.newsapp.presentation.details.events.DetailsEvent
import com.xavier.newsapp.presentation.details.screen.DetailsScreen
import com.xavier.newsapp.presentation.details.viewmodel.DetailsViewModel
import com.xavier.newsapp.presentation.home.screens.HomeScreen
import com.xavier.newsapp.presentation.home.viewmodels.HomeViewModel
import com.xavier.newsapp.presentation.nav_graph.Route
import com.xavier.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.xavier.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.xavier.newsapp.presentation.search.screens.SearchScreen
import com.xavier.newsapp.presentation.search.viewmodel.SearchViewModel
import com.xavier.newsapp.util.Constants

@Composable
fun NewsNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }


    Scaffold(
        bottomBar = {

            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> {
                                navigateToScreen(
                                    navController = navController,
                                    route = Route.HomeScreen.route
                                )
                            }

                            1 -> {
                                navigateToScreen(
                                    navController = navController,
                                    route = Route.SearchScreen.route,
                                )
                            }

                            2 -> {
                                navigateToScreen(
                                    navController = navController,
                                    route = Route.BookmarkScreen.route
                                )
                            }
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToScreen(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController, article = article)
                    })
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()

                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController, article = article)
                    }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                val isBookmarked = viewModel.isBookmarked
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>(Constants.NAV_ARTICLE)
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            onEvent = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            isBookmarked = isBookmarked
                        )
                    }


            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController, article = article)
                    }
                )
            }

        }

    }

}


private fun navigateToScreen(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Constants.NAV_ARTICLE, article)
    navController.navigate(Route.DetailsScreen.route)

}