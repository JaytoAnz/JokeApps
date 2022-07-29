package com.jayto.jakmall.domain.category.entity

import android.os.Parcelable
import com.jayto.jakmall.domain.subcategory.entity.SubCategory
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryAliases(
    val alias: String = "",
    val resolved: String = "",
    var subCategory: SubCategory? = null,
    var state: Int = -1
) : Parcelable
