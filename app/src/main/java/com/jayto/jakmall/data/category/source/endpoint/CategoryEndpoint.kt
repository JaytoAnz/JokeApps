package com.jayto.jakmall.data.category.source.endpoint

import com.jayto.jakmall.data.category.entity.CategoriesResponse
import retrofit2.http.GET

interface CategoryEndpoint {
    @GET("categories")
    suspend fun getCategories() : CategoriesResponse
}