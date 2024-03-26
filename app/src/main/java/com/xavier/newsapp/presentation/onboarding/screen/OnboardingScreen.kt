package com.xavier.newsapp.presentation.onboarding.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xavier.newsapp.data.models.onboarding.OnboardingData
import com.xavier.newsapp.data.models.onboarding.pages
import com.xavier.newsapp.presentation.onboarding.componets.OnBoardingButton
import com.xavier.newsapp.presentation.onboarding.componets.OnBoardingTextButton
import com.xavier.newsapp.presentation.onboarding.componets.PagerIndicator
import com.xavier.newsapp.presentation.onboarding.events.OnBoardingEvent
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onSaveEvent: (OnBoardingEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        OnboardingScreenContent(
            modifier = Modifier.padding(it),
            onSaveEvent = onSaveEvent
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    onSaveEvent: (OnBoardingEvent) -> Unit,
) {

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }




    HorizontalPager(state = pagerState, modifier = modifier.fillMaxSize()) { index ->
        OnboardingPage(page = pages[index], pagerState = pagerState, onSaveEvent = onSaveEvent)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(
    page: OnboardingData,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onSaveEvent: (OnBoardingEvent) -> Unit
) {

    val scope = rememberCoroutineScope()
    val lastPage = pages.size - 1

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Get Started")
                else -> listOf("", "")
            }
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(all = 16.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = page.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PagerIndicator(
                    modifier = Modifier.width(52.dp),
                    pagerSize = pages.size,
                    selectedPage = pagerState.currentPage
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (buttonState.value[0].isNotEmpty()) {
                        OnBoardingTextButton(
                            text = buttonState.value[0],
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                                }
                            })
                    }

                    OnBoardingButton(text = buttonState.value[1]) {
                        scope.launch {
                            if (pagerState.currentPage == lastPage) {
                                onSaveEvent(OnBoardingEvent.SaveAppEntry)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)

                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.padding(15.dp))
        }
    }
}

@Preview
@Composable
fun OnboardingPagePreview() {
    val onEvent = { event: OnBoardingEvent ->

    }

    OnboardingScreen(onSaveEvent = onEvent)
}



