package com.xavier.newsapp.presentation.onboarding.events

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
}