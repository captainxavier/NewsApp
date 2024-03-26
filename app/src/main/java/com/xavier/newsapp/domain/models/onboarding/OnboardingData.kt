package com.xavier.newsapp.domain.models.onboarding

import androidx.annotation.DrawableRes
import com.xavier.newsapp.R

data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    OnboardingData(
        title = "World News",
        description = "Mauris faucibus mi vitae libero mollis, et scelerisque lacus condimentum. Maecenas nec dolor vitae odio pharetra lacinia. Curabitur sed purus dapibus, imperdiet turpis sed, dignissim sapien.",
        image = R.drawable.dice
    ),
    OnboardingData(
        title = "Tech, Art, Culture News",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed facilisis elit quis ligula elementum efficitur. Nulla vulputate lacinia ante, sit amet ullamcorper massa imperdiet vitae. ",
        image = R.drawable.art
    ),
    OnboardingData(
        title = "Nature News",
        description = "Aenean ultrices iaculis neque ut condimentum. Quisque vitae venenatis erat, eu molestie neque. Vestibulum molestie velit et ipsum lacinia gravida. Duis scelerisque eros tristique, bibendum lectus quis, vulputate tellus.",
        image = R.drawable.bird
    )
)
