package com.xavier.newsapp.data.local.entities

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.xavier.newsapp.domain.models.news.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}