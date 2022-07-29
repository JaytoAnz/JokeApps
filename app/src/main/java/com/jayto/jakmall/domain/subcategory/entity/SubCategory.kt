package com.jayto.jakmall.domain.subcategory.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubCategory(
    var amount: Int = -1,
    val jokes: List<Joke> = emptyList(),
    val error: Boolean = false,
    val code: Int = -1,
    val message: String = "",
) : Parcelable