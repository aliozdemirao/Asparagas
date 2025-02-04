package com.aliozdemir.asparagas.di

import com.aliozdemir.asparagas.data.repository.NewsRepositoryImpl
import com.aliozdemir.asparagas.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}
