package com.aliozdemir.asparagas.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.usecase.GetTopHeadlinesUseCase
import com.aliozdemir.asparagas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
) : ViewModel() {

    private val _news = MutableLiveData<Resource<News>>()
    val news: LiveData<Resource<News>> get() = _news

    init {
        getTopHeadlines(
            country = null,
            category = "technology",
            sources = null,
            query = null,
            pageSize = null,
            page = null
        )
    }

    fun getTopHeadlines(
        country: String?,
        category: String?,
        sources: String?,
        query: String?,
        pageSize: Int?,
        page: Int?,
    ) {
        viewModelScope.launch {
            val result = getTopHeadlinesUseCase(country, category, sources, query, pageSize, page)
            _news.postValue(result)
        }
    }
}
