package com.xavier.newsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.xavier.newsapp.data.local.NewsDatabase
import com.xavier.newsapp.data.local.dao.NewsDao
import com.xavier.newsapp.data.local.entities.NewsTypeConvertor
import com.xavier.newsapp.data.remote.news.api.NewsApi
import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManagerImpl
import com.xavier.newsapp.data.repositories.local_user_manager.LocalUserManger
import com.xavier.newsapp.data.repositories.news.NewsRepository
import com.xavier.newsapp.data.repositories.news.NewsRepositoryImp
import com.xavier.newsapp.domain.usecases.app_entry.AppEntryUseCase
import com.xavier.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.xavier.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.xavier.newsapp.domain.usecases.news.DeleteArticle
import com.xavier.newsapp.domain.usecases.news.GetNews
import com.xavier.newsapp.domain.usecases.news.GetNewsUseCase
import com.xavier.newsapp.domain.usecases.news.SearchNews
import com.xavier.newsapp.domain.usecases.news.SelectArticle
import com.xavier.newsapp.domain.usecases.news.SelectArticles
import com.xavier.newsapp.domain.usecases.news.UpsertArticle
import com.xavier.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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


    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi,newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImp(newsApi = newsApi, newsDao = newsDao)
    }

    @Provides
    @Singleton
    fun providesNewsUseCase(
        repository: NewsRepository
    ): GetNewsUseCase {
        return GetNewsUseCase(
            getNews = GetNews(repository = repository),
            searchNews = SearchNews(repository = repository),
            upsertArticle = UpsertArticle(repository = repository),
            deleteArticle = DeleteArticle(repository=repository),
            selectArticles = SelectArticles(repository=repository),
            selectArticle = SelectArticle(repository=repository)
        )
    }


    @Provides
    @Singleton
    fun providesNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constants.NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}