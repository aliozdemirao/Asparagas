package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import com.aliozdemir.asparagas.util.Resource
import javax.inject.Inject

class GetEverythingUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(
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
        return newsRepository.getEverything(
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
        )
    }
}
