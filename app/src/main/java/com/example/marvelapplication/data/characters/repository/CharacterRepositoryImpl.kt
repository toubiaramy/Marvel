package com.example.marvelapplication.data.characters.repository

import androidx.lifecycle.LiveData
import com.example.marvelapplication.data.characters.models.Character
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val charactersDao: CharactersDao
) : CharacterRepository {

    override suspend fun insertCharactersToLocalStorage(data: List<Character>) {
        charactersDao.insertCharacters(data)
    }

    override suspend fun deleteCharacter(character: Character) {
        charactersDao.deleteCharacter(character)
    }

    override val readDataFromLocalStorage: LiveData<List<Character>> =
        charactersDao.readAllData()
}