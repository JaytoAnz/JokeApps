package com.jayto.jakmall.data.category.entity

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    val categories: List<String>? = emptyList(),
    @SerializedName("categoryAliases")
    val categoryAliasResponses: List<CategoryAliaseResponse>? = emptyList(),
    val error: Boolean = false,
    val timestamp: Long = -1,
)