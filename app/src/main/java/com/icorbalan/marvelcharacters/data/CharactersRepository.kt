package com.icorbalan.marvelcharacters.data

import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.data.network.MarvelService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: MarvelService,
    private val charactersProvider: CharactersProvider
) {

    suspend fun getCharacters() {
        val response = api.getCharacters()

        if (response.charactersResponse != null) {
            charactersProvider.characters = response.charactersResponse.data.results
        } else {
            charactersProvider.errorMessage = response.errorResponse
        }
    }

    suspend fun getCharacter(characterId: Int) {
        val response = api.getCharacter(characterId)

        if (response.charactersResponse != null) {
            charactersProvider.character = response.charactersResponse.data.results.first()
        } else {
            charactersProvider.errorMessage = response.errorResponse
        }
    }
}