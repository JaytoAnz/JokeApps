package com.jayto.jakmall.data.category

import com.jayto.jakmall.data.category.mapper.CategoriesResponseMapper
import com.jayto.jakmall.data.category.source.CategoryRemoteDataSource
import com.jayto.jakmall.domain.category.entity.Categories
import com.jayto.jakmall.domain.category.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryRemoteDataSource
) : CategoryRepository {
    override suspend fun getCategories(): Categories {
        return CategoriesResponseMapper().invoke(remoteDataSource.getCategories())
    }
}