package com.icorbalan.marvelcharacters.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.domain.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    val characterModel = MutableLiveData<List<CharacterModel>>()
    val isLoading = MutableLiveData<Boolean>()

    val getCharactersUseCase = GetCharactersUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCharactersUseCase()
            if (!result.isNullOrEmpty()) {
                randomCharacter()
                isLoading.postValue(false)
            }
        }
    }

    fun randomCharacter() {
        characterModel.postValue(CharactersProvider.characters)
    }



}