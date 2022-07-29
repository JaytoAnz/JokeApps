package com.jayto.jakmall.data.category.mapper

import com.jayto.jakmall.domain.category.entity.Categories

class CategoriesResponseMapper : (com.jayto.jakmall.data.category.entity.CategoriesResponse) -> Categories {
    override fun invoke(response: com.jayto.jakmall.data.category.entity.CategoriesResponse): Categories {
        return Categories(
            categories = response.categories.orEmpty(),
            categoryAliases = com.jayto.jakmall.data.category.mapper.CategoryAliasesResponseMapper()
                .invoke(response.categoryAliasResponses.orEmpty()),
            error = response.error,
            timestamp = response.timestamp
        )
    }
}