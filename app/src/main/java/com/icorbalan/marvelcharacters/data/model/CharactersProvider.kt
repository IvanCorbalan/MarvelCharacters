package com.icorbalan.marvelcharacters.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersProvider @Inject constructor() {
    var characters: List<CharacterModel> = emptyList()
}