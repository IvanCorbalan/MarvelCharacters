package com.icorbalan.marvelcharacters.data.network

import com.icorbalan.marvelcharacters.BuildConfig
import com.icorbalan.marvelcharacters.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiClient {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("hash") hash: String,
        @Query("ts") timestamp: Long,
        @Query("apikey") apikey: String = BuildConfig.PUBLIC_KEY,
    ): Response<CharactersResponse>

}