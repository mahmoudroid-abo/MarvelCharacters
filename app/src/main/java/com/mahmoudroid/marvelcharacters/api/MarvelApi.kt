package com.mahmoudroid.marvelcharacters.api

import com.mahmoudroid.marvelcharacters.models.Character
import com.mahmoudroid.marvelcharacters.models.Result
import com.mahmoudroid.marvelcharacters.utils.Constants.Companion.APIKEY
import com.mahmoudroid.marvelcharacters.utils.Constants.Companion.HASH
import com.mahmoudroid.marvelcharacters.utils.Constants.Companion.PAGE_SIZE
import com.mahmoudroid.marvelcharacters.utils.Constants.Companion.TS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts")
        ts: String = TS,
        @Query("apikey")
        apiKey: String = APIKEY,
        @Query("hash")
        hash: String = HASH,
        @Query("offset") offset: Int = 0,
        @Query("limit") size: Int = PAGE_SIZE,
    ): Response<Character>

    @GET("characters")
    suspend fun searchCharacters(
        @Query("searchQuery") searchQuery: String,
        @Query("ts")
        ts: String = TS,
        @Query("apikey")
        apiKey: String = APIKEY,
        @Query("hash")
        hash: String = HASH
    ): Response<Result>

}