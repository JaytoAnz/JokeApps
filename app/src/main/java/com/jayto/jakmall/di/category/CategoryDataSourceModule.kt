package com.jayto.jakmall.di.category

import com.jayto.jakmall.data.category.source.CategoryRemoteDataSource
import com.jayto.jakmall.data.category.source.CategoryRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryDataSourceModule {

    @Binds
    abstract fun bindsCategoryRemoteDataSource(
        categoryRemoteDataSourceImpl: CategoryRemoteDataSourceImpl
    ) : CategoryRemoteDataSource
}