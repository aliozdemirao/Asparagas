package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import javax.inject.Inject

class GetAllBookmarkedArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): List<Article> {
        return newsRepository.getAllBookmarkedArticles()
    }
}
