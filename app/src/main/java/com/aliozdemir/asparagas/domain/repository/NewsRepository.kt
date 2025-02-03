package com.aliozdemir.asparagas.domain.repository

import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.util.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?,
    ): Resource<News>

    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(url: String)
    suspend fun getAllBookmarkedArticles(): List<Article>
    suspend fun deleteAllArticles()
    suspend fun isArticleExists(url: String): Boolean
}
