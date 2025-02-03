package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String) {
        newsRepository.deleteArticle(url)
    }
}
