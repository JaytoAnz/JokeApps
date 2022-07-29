package com.jayto.jakmall.domain.category.repository

import com.jayto.jakmall.domain.category.entity.Categories

interface CategoryRepository {
    suspend fun getCategories() : Categories
}