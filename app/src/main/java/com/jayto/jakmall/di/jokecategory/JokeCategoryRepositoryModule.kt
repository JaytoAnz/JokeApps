package com.jayto.jakmall.di.jokecategory

import com.jayto.jakmall.data.joke.JokeRepositoryImpl
import com.jayto.jakmall.domain.subcategory.repository.JokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class JokeCategoryRepositoryModule {
    @Binds
    abstract fun bindsJokeRepository(
        jokeRepositoryImpl: JokeRepositoryImpl
    ) : JokeRepository
}