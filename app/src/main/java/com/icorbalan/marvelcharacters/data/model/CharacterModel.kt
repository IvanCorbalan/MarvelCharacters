package com.icorbalan.marvelcharacters.data.model

data class ApiResponse(
    val charactersResponse: CharactersResponse?,
    val errorResponse: String?
)

data class CharactersResponse(
    val data: DataDto
)

data class DataDto(
    val results: List<CharacterModel>
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ImageDto
)

data class ImageDto(
    val path: String,
    val extension: String
) {
    fun imageUrl() = "${path}.${extension}"
}

