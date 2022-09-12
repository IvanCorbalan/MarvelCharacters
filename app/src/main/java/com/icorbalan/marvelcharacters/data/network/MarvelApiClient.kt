package com.icorbalan.marvelcharacters.data.network

import com.icorbalan.marvelcharacters.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiClient {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("hash") hash: String,
        @Query("ts") timestamp: Long = 1,
        @Query("apikey") apikey: String = "e32c87db6db15d87da04710976a69db0",
    ): Response<CharactersResponse>

}