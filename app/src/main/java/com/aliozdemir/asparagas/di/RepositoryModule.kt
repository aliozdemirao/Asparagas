package com.aliozdemir.asparagas.di

import com.aliozdemir.asparagas.data.repository.NewsRepositoryImpl
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}
