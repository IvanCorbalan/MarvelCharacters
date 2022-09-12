package com.icorbalan.marvelcharacters.data

import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider.Companion.characters
import com.icorbalan.marvelcharacters.data.network.MarvelService

class CharactersRepository {

    private val api = MarvelService()
    suspend fun getCharacters(): List<CharacterModel> {
        val response = api.getCharacters()
        characters = response.data.results
        return characters
    }
}