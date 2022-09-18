package com.icorbalan.marvelcharacters.data.network

import android.content.Context
import com.icorbalan.marvelcharacters.BuildConfig
import com.icorbalan.marvelcharacters.R
import com.icorbalan.marvelcharacters.data.model.ApiResponse
import com.icorbalan.marvelcharacters.data.model.CharactersResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class MarvelService @Inject constructor(
    private val apiClient: MarvelApiClient,
    @ApplicationContext val context: Context
) {
    suspend fun getCharacters(): ApiResponse {
        val timestamp = System.currentTimeMillis()
        return withContext(Dispatchers.IO) {
            getCharactersFromApi(timestamp)
        }
    }

    private suspend fun getCharactersFromApi(timestamp: Long) : ApiResponse = try {
        val response = apiClient.getCharacters(
            generateMd5Hash(timestamp),
            timestamp
        )
        if (response.isSuccessful) {
            ApiResponse(response.body(), null)
        } else {
            ApiResponse(null, response.message())
        }

    } catch(e: Exception) {
        ApiResponse(null, context.getString(R.string.generic_error))
    }

    private fun generateMd5Hash(
        timestamp: Long,
    ): String {
        val md = MessageDigest.getInstance("MD5")
        val input = timestamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}