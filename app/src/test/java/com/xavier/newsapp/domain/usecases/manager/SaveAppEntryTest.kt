package com.xavier.newsapp.domain.usecases.manager

import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManger
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class SaveAppEntryTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockLocalUserManager: LocalUserManger

    private lateinit var saveAppEntry: SaveAppEntry

    @Before
    fun setUp() {
        saveAppEntry = SaveAppEntry(manager = mockLocalUserManager)
    }

    @Test
    fun `invokes saveAppEntry on LocalUserManager`(): Unit = runBlocking {
        saveAppEntry()
        `when`(mockLocalUserManager.saveAppEntry()).thenAnswer {  }

    }
}