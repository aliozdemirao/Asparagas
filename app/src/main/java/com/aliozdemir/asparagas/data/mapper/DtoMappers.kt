package com.aliozdemir.asparagas.data.mapper

import com.aliozdemir.asparagas.data.remote.dto.ArticleDto
import com.aliozdemir.asparagas.data.remote.dto.NewsDto
import com.aliozdemir.asparagas.data.remote.dto.SourceDto
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.model.Source

fun SourceDto.toSource(): Source = Source(
    id = this.id,
    name = this.name,
)

fun ArticleDto.toArticle(): Article = Article(
    source = this.source?.toSource(),
    author = this.author,
    title = this.title,
    description = this.description,
    url = this.url,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt,
    content = this.content,
)

fun NewsDto.toNews(): News = News(
    status = this.status,
    totalResults = this.totalResults,
    articles = this.articles?.map { it?.toArticle() },
)
