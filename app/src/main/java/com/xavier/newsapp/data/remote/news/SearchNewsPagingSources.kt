package com.xavier.newsapp.data.remote.news

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xavier.newsapp.data.remote.news.api.NewsApi
import com.xavier.newsapp.domain.models.news.Article
import java.io.IOException

class SearchNewsPagingSources(
    private val newsApi: NewsApi,
    private val sources: String,
    private val searchQuery: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1

        return try {
            val response = newsApi.searchNews(
                sources = sources,
                page = page,
                searchQuery = searchQuery
            )
            totalNewsCount += response.articles.size
            val articles = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == response.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: IOException) {
            // IOException for network failures.
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}