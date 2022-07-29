package com.jayto.jakmall.data.joke.entity

data class JokeResponse(
    val category: String? = null,
    val flags: FlagsResponse? = null,
    val id: String? = null,
    val joke: String? = null,
    val lang: String? = null,
    val safe: Boolean = false,
    val type: String? = null
)