package com.icorbalan.marvelcharacters.domain

import com.icorbalan.marvelcharacters.data.CharactersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var charactersRepository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        getCharactersUseCase = GetCharactersUseCase(charactersRepository)
    }

    @Test
    fun `repository return list of characters`() = runBlocking {
        //Given
        coEvery { charactersRepository.getCharacters() } returns emptyList()

        // When
        getCharactersUseCase()

        //Then
        coVerify(exactly = 1) { charactersRepository.getCharacters() }
    }

}