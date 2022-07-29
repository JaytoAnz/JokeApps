package com.jayto.jakmall.view.page.list.viewmodel.entity

import com.jayto.jakmall.domain.subcategory.entity.SubCategory

data class SubCategoryState(
    val loading: Boolean = false,
    val subCategory: SubCategory? = null,
    val position: Int = -1,
    val errMsg: String? = null
)
