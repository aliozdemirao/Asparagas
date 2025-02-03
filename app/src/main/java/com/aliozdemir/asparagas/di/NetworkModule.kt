package com.aliozdemir.asparagas.di

import com.aliozdemir.asparagas.BuildConfig
import com.aliozdemir.asparagas.data.remote.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY)
                .build()
            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}