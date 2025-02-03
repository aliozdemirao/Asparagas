package com.aliozdemir.asparagas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliozdemir.asparagas.data.local.entity.BookmarkArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(articleEntity: BookmarkArticleEntity)

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteArticle(url: String)

    @Query("SELECT * FROM articles ORDER BY id DESC")
    suspend fun getAllBookmarkedArticles(): List<BookmarkArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()

    @Query("SELECT EXISTS (SELECT * FROM articles WHERE url = :url)")
    suspend fun isArticleExists(url: String): Boolean
}
