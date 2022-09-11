package com.icorbalan.marvelcharacters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icorbalan.marvelcharacters.model.CharacterModel
import com.icorbalan.marvelcharacters.model.CharactersProvider

class CharactersViewModel : ViewModel() {

    val characterModel = MutableLiveData<CharacterModel>()

    fun randomCharacter() {
        val character = CharactersProvider.random()
        characterModel.postValue(character)
    }

}