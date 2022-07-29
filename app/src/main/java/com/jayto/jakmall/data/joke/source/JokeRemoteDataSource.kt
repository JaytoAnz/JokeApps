package com.jayto.jakmall.data.joke.source

import com.jayto.jakmall.data.joke.entity.SubCategoryResponse

interface JokeRemoteDataSource {
    suspend fun getJokeCategory(category: String, amount: Int) : SubCategoryResponse
}