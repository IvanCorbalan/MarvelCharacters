package com.icorbalan.marvelcharacters.domain

import com.icorbalan.marvelcharacters.data.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke() = repository.getCharacters()
}