package com.xavier.newsapp.domain.usecases.manager

import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManger
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val manager: LocalUserManger
) {
    suspend operator fun invoke() {
        manager.saveAppEntry()
    }
}