package com.xavier.newsapp.presentation.nav_graph

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.xavier.newsapp.presentation.onboarding.screen.OnboardingScreen
import com.xavier.newsapp.presentation.onboarding.viewmodels.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnboardingScreen(onSaveEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {

            composable(route = Route.NewsNavigatorScreen.route) {
                Text(
                    text = "News Home Page",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
    }

}