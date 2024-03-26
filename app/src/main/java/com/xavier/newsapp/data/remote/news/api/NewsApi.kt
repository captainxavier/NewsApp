package com.xavier.newsapp.data.remote.news.api

import com.xavier.newsapp.data.remote.news.responses.NewsResponse
import com.xavier.newsapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey:String =Constants.API_KEY

    ):NewsResponse
}