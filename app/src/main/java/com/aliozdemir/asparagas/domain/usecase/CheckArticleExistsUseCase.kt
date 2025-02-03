package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.repository.NewsRepository
import javax.inject.Inject

class CheckArticleExistsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Boolean {
        return newsRepository.isArticleExists(url)
    }
}
