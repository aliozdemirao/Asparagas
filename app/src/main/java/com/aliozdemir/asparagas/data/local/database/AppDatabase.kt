package com.aliozdemir.asparagas.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aliozdemir.asparagas.data.local.dao.ArticleDao
import com.aliozdemir.asparagas.data.local.entity.BookmarkArticleEntity

@Database(entities = [BookmarkArticleEntity::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
