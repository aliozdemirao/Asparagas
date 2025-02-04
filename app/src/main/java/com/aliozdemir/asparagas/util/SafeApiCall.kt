package com.aliozdemir.asparagas.util

import android.content.Context
import com.aliozdemir.asparagas.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeApiCall @Inject constructor(@ApplicationContext val context: Context) {
    suspend inline fun <T> execute(crossinline body: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(
                withContext(Dispatchers.IO) {
                    body()
                }
            )
        } catch (e: Exception) {
            Resource.Error(parseException(e))
        }
    }

    fun parseException(e: Exception): String = when (e) {
        is IOException -> context.getString(R.string.error_connection)
        is HttpException -> {
            val errorBody = e.response()?.errorBody()?.string()
            parseErrorResponse(errorBody)
        }

        else -> context.getString(R.string.error_unknown)
    }

    fun parseErrorResponse(errorResponse: String?): String = errorResponse?.let { responseString ->
        try {
            val json = JSONObject(responseString)
            if (json.optString("status") == "error") {
                json.optString("message", context.getString(R.string.error_unknown))
            } else {
                context.getString(R.string.error_unknown)
            }
        } catch (ex: Exception) {
            context.getString(R.string.error_unknown)
        }
    } ?: context.getString(R.string.error_unknown)
}
