package com.jayto.jakmall.domain.category.usecase

import com.jayto.jakmall.domain.base.BaseUseCase
import com.jayto.jakmall.domain.category.entity.Categories
import com.jayto.jakmall.domain.category.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseUseCase<Unit, Categories>() {

    override suspend fun build(param: Unit): Categories {
        return categoryRepository.getCategories()
    }
}