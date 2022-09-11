package com.icorbalan.marvelcharacters.model

class CharactersProvider {

    companion object {

         fun random(): CharacterModel {
             val position: Int = (0..2).random()
             return characters[position]
         }


        private val characters = listOf<CharacterModel>(
            CharacterModel(1, "Panda", "Panda Description", "sarasa"),
            CharacterModel(2, "Panda 2", "Panda 2 Description", "sarasa"),
            CharacterModel(3, "Panda 3", "Panda 3 Description", "sarasa")
        )
    }
}