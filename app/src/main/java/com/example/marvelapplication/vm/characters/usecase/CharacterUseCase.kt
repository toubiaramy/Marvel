package com.example.marvelapplication.vm.characters.usecase

import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {
    suspend fun getCharacters(): Flow<CharactersResult>
}