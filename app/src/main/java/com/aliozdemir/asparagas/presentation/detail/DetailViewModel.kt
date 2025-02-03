package com.aliozdemir.asparagas.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.asparagas.domain.model.Article
import com.aliozdemir.asparagas.domain.usecase.CheckArticleExistsUseCase
import com.aliozdemir.asparagas.domain.usecase.InsertArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertArticleUseCase: InsertArticleUseCase,
    private val checkArticleExistsUseCase: CheckArticleExistsUseCase,
) : ViewModel() {

    private val _isArticleBookmarked = MutableLiveData<Boolean>()
    val isArticleBookmarked: LiveData<Boolean> get() = _isArticleBookmarked

    fun insertArticle(article: Article) {
        viewModelScope.launch {
            insertArticleUseCase(article)
            _isArticleBookmarked.postValue(true)
        }
    }

    fun initializeBookmark(article: Article) {
        viewModelScope.launch {
            val exists = checkArticleExistsUseCase(article.url!!)
            _isArticleBookmarked.postValue(exists)
        }
    }
}
