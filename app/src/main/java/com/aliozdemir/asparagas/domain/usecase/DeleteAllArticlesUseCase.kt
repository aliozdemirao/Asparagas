package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteAllArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() {
        newsRepository.deleteAllArticles()
    }
}
