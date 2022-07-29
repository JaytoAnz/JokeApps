package com.jayto.jakmall.data.joke.mapper

import com.jayto.jakmall.data.joke.entity.FlagsResponse
import com.jayto.jakmall.domain.subcategory.entity.Flags

class FlagsResponseMapper : (FlagsResponse) -> Flags {
    override fun invoke(response: FlagsResponse): Flags {
        return Flags(
            explicit = response.explicit,
            nsfw = response.nsfw,
            political = response.political,
            racist = response.racist,
            religious = response.religious,
            sexist = response.sexist,
        )
    }
}