package com.aliozdemir.asparagas.domain.model

data class News(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article?>?,
)
