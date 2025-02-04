package com.aliozdemir.asparagas.data.repository

import android.content.Context
import com.aliozdemir.asparagas.R
import com.aliozdemir.asparagas.data.local.dao.ArticleDao
import com.aliozdemir.asparagas.data.mapper.toArticle
import com.aliozdemir.asparagas.data.mapper.toBookmarkArticleEntity
import com.aliozdemir.asparagas.data.mapper.toNews
import com.aliozdemir.asparagas.data.remote.api.NewsApi
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import com.aliozdemir.asparagas.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    @ApplicationContext val context: Context,
    private val dao: ArticleDao,
) : NewsRepository {
    override suspend fun getTopHeadlines(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?,
    ): Resource<News> {
        return try {
            Resource.Success(
                withContext(Dispatchers.IO) {
                    api.getTopHeadlines(
                        country,
                        category,
                        sources,
                        query,
                        pageSize,
                        page
                    ).toNews()
                }
            )
        } catch (e: Exception) {
            return when (e) {
                is IOException -> Resource.Error(context.getString(R.string.error_connection))
                is HttpException -> {
                    val errorResponse = e.response()?.errorBody()?.string()
                    val apiError = parseErrorResponse(errorResponse)
                    Resource.Error(apiError)
                }

                else -> Resource.Error(context.getString(R.string.error_unknown))
            }
        }
    }

    override suspend fun getEverything(
        query: String?,
        searchIn: String?,
        sources: String?,
        domains: String?,
        excludeDomains: String?,
        from: String?,
        to: String?,
        language: String?,
        sortBy: String?,
        pageSize: Int?,
        page: Int?,
    ): Resource<News> {
        return try {
            Resource.Success(
                withContext(Dispatchers.IO) {
                    api.getEverything(
                        query,
                        searchIn,
                        sources,
                        domains,
                        excludeDomains,
                        from,
                        to,
                        language,
                        sortBy,
                        pageSize,
                        page
                    ).toNews()
                }
            )
        } catch (e: Exception) {
            return when (e) {
                is IOException -> Resource.Error(context.getString(R.string.error_connection))
                is HttpException -> {
                    val errorResponse = e.response()?.errorBody()?.string()
                    val apiError = parseErrorResponse(errorResponse)
                    Resource.Error(apiError)
                }

                else -> Resource.Error(context.getString(R.string.error_unknown))
            }
        }
    }

    private fun parseErrorResponse(errorResponse: String?): String {
        val jsonObject = errorResponse?.let { JSONObject(it) }
        if (jsonObject != null) {
            if (jsonObject.has("status") && jsonObject.getString("status") == "error") {
                val error = jsonObject.getString("message")
                return error
            } else {
                return context.getString(R.string.error_unknown)
            }
        } else {
            return context.getString(R.string.error_unknown)
        }
    }

    override suspend fun insertArticle(article: Article) {
        dao.insertArticle(article.toBookmarkArticleEntity())
    }

    override suspend fun deleteArticle(url: String) {
        dao.deleteArticle(url)
    }

    override suspend fun getAllBookmarkedArticles(): List<Article> {
        return dao.getAllBookmarkedArticles().map { it.toArticle() }
    }

    override suspend fun deleteAllArticles() {
        dao.deleteAllArticles()
    }

    override suspend fun isArticleExists(url: String): Boolean {
        return dao.isArticleExists(url)
    }
}
