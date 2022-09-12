package com.icorbalan.marvelcharacters.data.network

import com.icorbalan.marvelcharacters.BuildConfig
import com.icorbalan.marvelcharacters.core.RetrofitHelper
import com.icorbalan.marvelcharacters.data.model.CharactersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.MessageDigest

class MarvelService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharacters(): CharactersResponse {
        val timestamp = System.currentTimeMillis()
        return withContext(Dispatchers.IO) {
            val response =  retrofit.create(MarvelApiClient::class.java).getCharacters(
                generateMd5Hash(timestamp),
                timestamp
            )
            response.body()!!
        }
    }

    private fun generateMd5Hash(
        timestamp: Long,
    ): String {
        val md = MessageDigest.getInstance("MD5")
        val input = timestamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}