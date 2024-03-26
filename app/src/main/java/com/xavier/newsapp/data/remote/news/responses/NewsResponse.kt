package com.xavier.newsapp.data.remote.news.responses

import com.google.gson.annotations.SerializedName
import com.xavier.newsapp.domain.models.news.Article

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)