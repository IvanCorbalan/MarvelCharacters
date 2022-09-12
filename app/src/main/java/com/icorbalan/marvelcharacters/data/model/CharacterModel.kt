package com.icorbalan.marvelcharacters.data.model

data class CharactersResponse(
    val data: DataDto
)

class DataDto(
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val resourceURI: String
)
