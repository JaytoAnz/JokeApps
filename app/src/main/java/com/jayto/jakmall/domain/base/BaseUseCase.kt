package com.jayto.jakmall.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

abstract class BaseUseCase<in PARAM, RESULT : Any> {

    abstract suspend fun build(param: PARAM) : RESULT

    private fun execute(param: PARAM) : Flow<DomainResult<RESULT>> = flow {
        try {
            val data = build(param)
            emit(DomainResult.Success(data))
        } catch(e: HttpException) {
            emit(DomainResult.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(DomainResult.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    operator fun invoke(param: PARAM) : Flow<DomainResult<RESULT>> = execute(param)
}