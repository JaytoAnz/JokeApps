package com.jayto.jakmall.data.joke.source.endpoint

import com.jayto.jakmall.data.joke.entity.SubCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeEndpoint {

    @GET("joke/{category}")
    suspend fun getJokeCategory(
        @Path("category") category: String,
        @Query("type") type: String = "single",
        @Query("amount") amount: Int = 2
    ) : SubCategoryResponse
}