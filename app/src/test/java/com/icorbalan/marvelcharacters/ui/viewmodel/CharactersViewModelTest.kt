package com.icorbalan.marvelcharacters.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.data.model.CharactersProvider
import com.icorbalan.marvelcharacters.domain.GetCharactersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @RelaxedMockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @RelaxedMockK
    private lateinit var charactersProvider: CharactersProvider

    private lateinit var charactersViewModel: CharactersViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        charactersViewModel = CharactersViewModel(getCharactersUseCase, charactersProvider)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when call to getCharactersUseCase return characters`( ) = runTest {
        //Given
        val charactersList = listOf<CharacterModel>(mockk(relaxed = true), mockk(relaxed = true))
        coEvery { charactersProvider.characters } returns charactersList

        //When
        charactersViewModel.onCreate()

        //Then
        assert(charactersViewModel.characterModel.value == charactersList)
        Assert.assertFalse(charactersViewModel.isLoading.value as Boolean)
    }

    @Test
    fun `when call to getCharactersUseCase return empty list of characters`( ) = runTest {
        //Given
        val charactersList = listOf<CharacterModel>()
        val errorMessage = "error message"
        coEvery { charactersProvider.characters } returns charactersList
        coEvery { charactersProvider.errorMessage } returns errorMessage

        //When
        charactersViewModel.onCreate()

        //Then
        Assert.assertFalse(charactersViewModel.isLoading.value as Boolean)
        Assert.assertEquals(errorMessage, charactersViewModel.errorMessage.value)
    }
}