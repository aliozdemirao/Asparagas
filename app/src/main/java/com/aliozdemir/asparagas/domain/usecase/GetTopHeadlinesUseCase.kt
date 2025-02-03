package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import com.aliozdemir.asparagas.util.Resource
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?,
    ): Resource<News> {
        return newsRepository.getTopHeadlines(country, category, sources, query, pageSize, page)
    }
}
