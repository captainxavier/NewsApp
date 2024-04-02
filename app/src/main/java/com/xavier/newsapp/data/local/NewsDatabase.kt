package com.xavier.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xavier.newsapp.data.local.dao.NewsDao
import com.xavier.newsapp.data.local.entities.ArticleEntity
import com.xavier.newsapp.data.local.entities.NewsTypeConvertor

@Database(entities = [ArticleEntity::class], version = 3)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase:RoomDatabase() {

    abstract val newsDao:NewsDao
}