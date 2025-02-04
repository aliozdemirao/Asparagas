package com.aliozdemir.asparagas.data.repository

import com.aliozdemir.asparagas.data.local.dao.ArticleDao
import com.aliozdemir.asparagas.data.mapper.toArticle
import com.aliozdemir.asparagas.data.mapper.toBookmarkArticleEntity
import com.aliozdemir.asparagas.data.mapper.toNews
import com.aliozdemir.asparagas.data.remote.api.NewsApi
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import com.aliozdemir.asparagas.util.Resource
import com.aliozdemir.asparagas.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val safeApiCall: SafeApiCall,
    private val dao: ArticleDao,
) : NewsRepository {
    override suspend fun getTopHeadlines(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?,
    ): Resource<News> = safeApiCall.execute {
        api.getTopHeadlines(
            country,
            category,
            sources,
            query,
            pageSize,
            page
        ).toNews()
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
    ): Resource<News> = safeApiCall.execute {
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
