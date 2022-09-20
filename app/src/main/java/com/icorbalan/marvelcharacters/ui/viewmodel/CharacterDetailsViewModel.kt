package com.icorbalan.marvelcharacters.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.domain.GetCharacterUseCase
import com.icorbalan.marvelcharacters.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val charactersProvider: CharactersProvider
)  : ViewModel() {

    val characterModel = MutableLiveData<CharacterModel>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun onCreate(characterId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            getCharacterUseCase(characterId)

            charactersProvider.character?.let {
                characterModel.postValue(it)
            } ?: run {
                errorMessage.postValue(charactersProvider.errorMessage)
            }

            isLoading.postValue(false)
        }
    }
}