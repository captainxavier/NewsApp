package com.xavier.newsapp.data.repositories.local_user_manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LocalUserManagerImplTest {
    private lateinit var context: Context
    private lateinit var localUserManager: LocalUserManagerImpl
    private lateinit var mockDataStore: DataStore<Preferences>


    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        mockDataStore = context.testDatastore
        localUserManager =
            LocalUserManagerImpl(context = context)
    }

    @Test
    fun saveAppEntrySavesTrueToDataStore() = runBlocking {

        // Call the function
        localUserManager.saveAppEntry()

        // Verify data is saved
        val data = localUserManager.readAppEntry().first()
        assertThat(data).isTrue()
    }

    @Test
    fun readAppEntryEmitsFalseIfDataDoesNotExist(): Unit = runBlocking {
        // Launch flow collection
        val flow = localUserManager.readAppEntry()
        val value = flow.first()
        // Verify data is emitted
        assertThat(value).isFalse()
    }

    private val Context.testDatastore: DataStore<Preferences> by preferencesDataStore(name = "TEST_DATA_STORE")
}