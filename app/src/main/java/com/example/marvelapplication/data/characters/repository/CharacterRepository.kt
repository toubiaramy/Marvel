package com.example.marvelapplication.data.characters.repository

import androidx.lifecycle.LiveData
import com.example.marvelapplication.data.characters.models.Character

interface CharacterRepository {
    suspend fun insertCharactersToLocalStorage(data: List<Character>)
    suspend fun deleteCharacter(character: Character)

    val readDataFromLocalStorage: LiveData<List<Character>>
}