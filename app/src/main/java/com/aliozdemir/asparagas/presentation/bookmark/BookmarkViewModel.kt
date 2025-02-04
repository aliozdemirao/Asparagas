package com.aliozdemir.asparagas.presentation.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.usecase.DeleteAllArticlesUseCase
import com.aliozdemir.asparagas.domain.usecase.DeleteArticleUseCase
import com.aliozdemir.asparagas.domain.usecase.GetAllBookmarkedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBookmarkedArticlesUseCase: GetAllBookmarkedArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val deleteAllArticlesUseCase: DeleteAllArticlesUseCase,
) : ViewModel() {

    private val _bookmarkedArticles = MutableLiveData<List<Article>>()
    val bookmarkedArticles: LiveData<List<Article>> = _bookmarkedArticles

    fun getAllBookmarkedArticles() {
        viewModelScope.launch {
            val articles = getAllBookmarkedArticlesUseCase()
            _bookmarkedArticles.postValue(articles)
        }
    }

    fun deleteArticle(url: String) {
        viewModelScope.launch {
            deleteArticleUseCase(url)
            getAllBookmarkedArticles()
        }
    }

    fun deleteAllArticles() {
        viewModelScope.launch {
            deleteAllArticlesUseCase()
            getAllBookmarkedArticles()
        }
    }
}
