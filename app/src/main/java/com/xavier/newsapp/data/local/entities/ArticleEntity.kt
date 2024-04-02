package com.xavier.newsapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xavier.newsapp.domain.models.news.Article
import com.xavier.newsapp.domain.models.news.Source

@Entity
data class ArticleEntity(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
)


fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

fun ArticleEntity.toDto(): Article {
    return Article(
        author = author?:"",
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}
