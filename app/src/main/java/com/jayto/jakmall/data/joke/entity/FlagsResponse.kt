package com.jayto.jakmall.data.joke.entity

data class FlagsResponse(
    val explicit: Boolean = false,
    val nsfw: Boolean = false,
    val political: Boolean = false,
    val racist: Boolean = false,
    val religious: Boolean = false,
    val sexist: Boolean = false
)