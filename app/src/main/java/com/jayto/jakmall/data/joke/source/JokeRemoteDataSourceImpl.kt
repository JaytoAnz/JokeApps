package com.jayto.jakmall.data.joke.source

import com.jayto.jakmall.data.joke.entity.SubCategoryResponse
import com.jayto.jakmall.data.joke.source.endpoint.JokeEndpoint
import javax.inject.Inject

class JokeRemoteDataSourceImpl @Inject constructor(
    private val jokeEndpoint: JokeEndpoint
) : JokeRemoteDataSource {

    override suspend fun getJokeCategory(category: String, amount: Int): SubCategoryResponse {
        return jokeEndpoint.getJokeCategory(category = category, amount = amount)
    }
}