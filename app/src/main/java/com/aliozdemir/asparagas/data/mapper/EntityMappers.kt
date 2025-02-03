package com.aliozdemir.asparagas.data.mapper

import com.aliozdemir.asparagas.data.local.entity.BookmarkArticleEntity
import com.aliozdemir.asparagas.data.local.entity.BookmarkSourceEntity
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.model.Source

fun Article.toBookmarkArticleEntity(): BookmarkArticleEntity = BookmarkArticleEntity(
    source = source?.toSourceEntity(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
)

fun BookmarkArticleEntity.toArticle(): Article = Article(
    source = source?.toSource(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content,
)

fun Source.toSourceEntity(): BookmarkSourceEntity = BookmarkSourceEntity(
    id = id,
    name = name,
)

fun BookmarkSourceEntity.toSource(): Source = Source(
    id = id,
    name = name,
)
