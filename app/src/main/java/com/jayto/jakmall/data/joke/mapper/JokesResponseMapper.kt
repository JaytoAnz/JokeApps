package com.jayto.jakmall.data.joke.mapper

import com.jayto.jakmall.data.joke.entity.JokeResponse
import com.jayto.jakmall.domain.subcategory.entity.Flags
import com.jayto.jakmall.domain.subcategory.entity.Joke
import com.jayto.jakmall.utils.Utils.validInt

class JokesResponseMapper : (JokeResponse) -> Joke {
    override fun invoke(response: JokeResponse): Joke {
        val flagsResponse = response.flags
        return Joke(
            category = response.category.orEmpty(),
            flags = if (flagsResponse != null) FlagsResponseMapper().invoke(flagsResponse) else Flags(),
            id = response.id.orEmpty().validInt(),
            joke = response.joke.orEmpty(),
            lang = response.lang.orEmpty(),
            safe = response.safe,
            type = response.type.orEmpty()
        )
    }
}