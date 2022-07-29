package com.jayto.jakmall.data.joke

import com.jayto.jakmall.data.joke.mapper.SubCategoryResponseMapper
import com.jayto.jakmall.data.joke.source.JokeRemoteDataSource
import com.jayto.jakmall.domain.subcategory.entity.SubCategory
import com.jayto.jakmall.domain.subcategory.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val remoteDataSource: JokeRemoteDataSource
) : JokeRepository {
    override suspend fun getJokeCategory(category: String, amount: Int): SubCategory {
        return SubCategoryResponseMapper().invoke(remoteDataSource.getJokeCategory(category, amount))
    }
}