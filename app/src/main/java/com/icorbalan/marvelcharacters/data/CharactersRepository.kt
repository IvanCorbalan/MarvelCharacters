package com.icorbalan.marvelcharacters.data

import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.data.network.MarvelService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: MarvelService,
    private val charactersProvider: CharactersProvider
) {

    suspend fun getCharacters(): List<CharacterModel> {
        val response = api.getCharacters()
        charactersProvider.characters = response.data.results
        return charactersProvider .characters
    }
}