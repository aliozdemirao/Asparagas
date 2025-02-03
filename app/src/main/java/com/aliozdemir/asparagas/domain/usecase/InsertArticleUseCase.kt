package com.aliozdemir.asparagas.domain.usecase

import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import javax.inject.Inject

class InsertArticleUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.insertArticle(article)
    }
}
