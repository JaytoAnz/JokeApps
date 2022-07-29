package com.jayto.jakmall.utils

import com.jayto.jakmall.domain.category.entity.CategoryAliases

object Utils {
    fun String.validInt() : Int {
        return try {
            this.toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }

    fun CategoryAliases.getAmount(): Int {
        return when (val subCategory = this.subCategory) {
            null -> 2
            else -> when {
                subCategory.amount <= 2 -> subCategory.amount + 2
                else -> 2
            }
        }
    }
}