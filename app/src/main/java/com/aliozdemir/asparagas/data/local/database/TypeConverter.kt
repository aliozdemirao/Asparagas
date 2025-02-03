package com.aliozdemir.asparagas.data.local.database

import androidx.room.TypeConverter
import com.aliozdemir.asparagas.data.local.entity.BookmarkSourceEntity
import com.google.gson.Gson

class TypeConverter {
    companion object {
        private val gson = Gson()
    }

    @TypeConverter
    fun fromSource(source: BookmarkSourceEntity?): String? {
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String?): BookmarkSourceEntity? {
        return sourceString?.let {
            gson.fromJson(it, BookmarkSourceEntity::class.java)
        }
    }
}
