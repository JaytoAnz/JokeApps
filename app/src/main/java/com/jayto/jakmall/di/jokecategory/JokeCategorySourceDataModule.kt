package com.jayto.jakmall.di.jokecategory

import com.jayto.jakmall.data.joke.source.JokeRemoteDataSource
import com.jayto.jakmall.data.joke.source.JokeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JokeCategorySourceDataModule {
    @Binds
    abstract fun bindsJokeCategoryRemoteDataSource(
        jokeRemoteDataSourceImpl: JokeRemoteDataSourceImpl
    ) : JokeRemoteDataSource
}