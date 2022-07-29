package com.jayto.jakmall.data.joke.mapper

import com.jayto.jakmall.data.joke.entity.SubCategoryResponse
import com.jayto.jakmall.domain.subcategory.entity.SubCategory
import com.jayto.jakmall.utils.Utils.validInt

class SubCategoryResponseMapper : (SubCategoryResponse) -> SubCategory {
    override fun invoke(response: SubCategoryResponse): SubCategory {
        return SubCategory(
            error = response.error,
            code = response.code,
            message = response.message.orEmpty(),
            amount = response.amount.orEmpty().validInt(),
            jokes = response.jokes.orEmpty().map { JokesResponseMapper().invoke(it) }
        )
    }
}