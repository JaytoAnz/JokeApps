package com.jayto.jakmall.data.category.mapper

import com.jayto.jakmall.domain.category.entity.CategoryAliases

class CategoryAliasesResponseMapper : (List<com.jayto.jakmall.data.category.entity.CategoryAliaseResponse>) -> List<CategoryAliases> {
    override fun invoke(response: List<com.jayto.jakmall.data.category.entity.CategoryAliaseResponse>): List<CategoryAliases> {
        return response.map {
            CategoryAliases(
                alias = it.alias.orEmpty(),
                resolved = it.resolved.orEmpty()
            )
        }
    }
}