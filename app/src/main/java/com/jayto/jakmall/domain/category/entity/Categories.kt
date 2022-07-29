package com.jayto.jakmall.domain.category.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
    val categories: List<String> = emptyList(),
    val categoryAliases: List<CategoryAliases> = emptyList(),
    val error: Boolean = false,
    val timestamp: Long = -1
) : Parcelable
