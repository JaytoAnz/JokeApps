package com.jayto.jakmall.domain.subcategory.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Joke(
    val category: String = "",
    val flags: Flags = Flags(),
    val id: Int = -1,
    val joke: String = "",
    val lang: String = "",
    val safe: Boolean = false,
    val type: String = ""
) : Parcelable