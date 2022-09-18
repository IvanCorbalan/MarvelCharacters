package com.icorbalan.marvelcharacters.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val charactersProvider: CharactersProvider
)  : ViewModel() {

    val characterModel = MutableLiveData<List<CharacterModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            getCharactersUseCase()
            if (charactersProvider.characters.isNotEmpty()) {
                characterModel.postValue(charactersProvider.characters)
            } else {
                errorMessage.postValue(charactersProvider.errorMessage)
            }
            isLoading.postValue(false)
        }
    }
}