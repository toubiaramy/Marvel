package com.example.marvelapplication.vm.characters.usecase

import androidx.lifecycle.LiveData
import com.example.marvelapplication.data.characters.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    val readDataFromLocalStorage: LiveData<List<Character>>

    suspend fun getCharacters(): Flow<CharactersResult>
    suspend fun insertDataToLocalStorage(data: List<Character>)
    suspend fun deleteCharacter(character: Character)
}