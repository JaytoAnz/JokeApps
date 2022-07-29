package com.jayto.jakmall.data.category.source

import com.jayto.jakmall.data.category.entity.CategoriesResponse
import com.jayto.jakmall.data.category.source.endpoint.CategoryEndpoint
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val endpoint: CategoryEndpoint
) : CategoryRemoteDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return endpoint.getCategories()
    }
}