package com.jayto.jakmall.di

import com.jayto.jakmall.BuildConfig
import com.jayto.jakmall.data.category.source.endpoint.CategoryEndpoint
import com.jayto.jakmall.data.joke.source.endpoint.JokeEndpoint
import com.jayto.jakmall.di.qualifier.HttpLoggingInterceptorQualifier
import com.jayto.jakmall.di.qualifier.NetworkInterceptorQualifier
import com.jayto.jakmall.utils.net.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @HttpLoggingInterceptorQualifier
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }

    @NetworkInterceptorQualifier
    @Singleton
    @Provides
    fun provideInterceptor() : Interceptor = NetworkInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @NetworkInterceptorQualifier networkInterceptor: Interceptor,
        @HttpLoggingInterceptorQualifier httpLoggingInterceptor: Interceptor
    ) : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        okHttpClient.addInterceptor(networkInterceptor)
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Named("gson-retrofit")
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.APP_ENDPOINT)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideCategoryEndpoint(
        @Named("gson-retrofit") retrofit: Retrofit
    ): CategoryEndpoint = retrofit.create(CategoryEndpoint::class.java)

    @Singleton
    @Provides
    fun provideJokeEndpoint(
        @Named("gson-retrofit") retrofit: Retrofit
    ): JokeEndpoint = retrofit.create(JokeEndpoint::class.java)
}