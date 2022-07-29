package com.jayto.jakmall.domain.subcategory.repository

import com.jayto.jakmall.domain.subcategory.entity.SubCategory

interface JokeRepository {
    suspend fun getJokeCategory(category: String, amount: Int) : SubCategory
}