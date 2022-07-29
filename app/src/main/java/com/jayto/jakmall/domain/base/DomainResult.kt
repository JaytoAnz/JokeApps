package com.jayto.jakmall.domain.base

sealed class DomainResult <out T: Any>{
    data class Success<T : Any>(
        val data: T
    ) : DomainResult<T>()

    data class Error(
        val errMsg: String
    ) : DomainResult<Nothing>()
}