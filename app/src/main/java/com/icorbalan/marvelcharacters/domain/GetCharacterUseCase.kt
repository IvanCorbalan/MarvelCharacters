package com.icorbalan.marvelcharacters.domain

import com.icorbalan.marvelcharacters.data.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(characterId: Int) = repository.getCharacter(characterId)
}