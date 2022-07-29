package com.jayto.jakmall.domain.subcategory.usecase

import com.jayto.jakmall.domain.base.BaseUseCase
import com.jayto.jakmall.domain.subcategory.entity.GetJokeRequest
import com.jayto.jakmall.domain.subcategory.entity.SubCategory
import com.jayto.jakmall.domain.subcategory.repository.JokeRepository
import javax.inject.Inject

class GetJokeCategoryUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) : BaseUseCase<GetJokeRequest, SubCategory>() {
    override suspend fun build(param: GetJokeRequest): SubCategory {
        return jokeRepository.getJokeCategory(param.category, param.amount)
    }
}