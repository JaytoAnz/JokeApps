package com.jayto.jakmall.data.category.source

interface CategoryRemoteDataSource {
    suspend fun getCategories() : com.jayto.jakmall.data.category.entity.CategoriesResponse
}