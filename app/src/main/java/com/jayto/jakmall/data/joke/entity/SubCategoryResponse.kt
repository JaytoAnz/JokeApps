package com.jayto.jakmall.data.joke.entity

data class SubCategoryResponse(
    val amount: String? = null,
    val jokes: List<JokeResponse>? = emptyList(),
    val error: Boolean = false,
    val code: Int = -1,
    val message: String? = null,
)