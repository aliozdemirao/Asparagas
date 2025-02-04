package com.aliozdemir.asparagas.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliozdemir.asparagas.domain.model.News
import com.aliozdemir.asparagas.domain.usecase.GetEverythingUseCase
import com.aliozdemir.asparagas.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getEverythingUseCase: GetEverythingUseCase,
) : ViewModel() {

    private val _news = MutableLiveData<Resource<News>>()
    val news: LiveData<Resource<News>> get() = _news

    fun getEverything(
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
    ) {
        viewModelScope.launch {
            val result = getEverythingUseCase(
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
            _news.postValue(result)
        }
    }
}
