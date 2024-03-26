package com.xavier.newsapp.domain.usecases.app_entry

import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManger
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val manager: LocalUserManger
) {
    operator fun invoke(): Flow<Boolean> {
        return manager.readAppEntry()
    }
}