package com.jayto.jakmall.view.page.list.viewmodel.entity

import com.jayto.jakmall.domain.category.entity.Categories

data class CategoryState(
    val loading: Boolean = false,
    val category: Categories? = null,
    val errMsg: String? = null
)
