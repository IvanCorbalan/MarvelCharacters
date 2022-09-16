package com.icorbalan.marvelcharacters.domain

import com.icorbalan.marvelcharacters.data.CharactersRepository
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(): List<CharacterModel>? = repository.getCharacters()
}