package com.xavier.newsapp.di

import android.app.Application
import android.content.Context
import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManagerImpl
import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManger
import com.xavier.newsapp.domain.usecases.manager.AppEntryUseCase
import com.xavier.newsapp.domain.usecases.manager.ReadAppEntry
import com.xavier.newsapp.domain.usecases.manager.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModel {

    @Provides
    @Singleton
    fun providesLocalUserManager(@ApplicationContext context: Context): LocalUserManger =
        LocalUserManagerImpl(context = context)

    @Provides
    @Singleton
    fun providesAppEntry(manager: LocalUserManger) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(manager = manager),
        saveAppEntry = SaveAppEntry(manager = manager)
    )
}