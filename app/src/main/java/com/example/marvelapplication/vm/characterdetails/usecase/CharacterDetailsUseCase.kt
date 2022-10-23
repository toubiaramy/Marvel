package com.example.marvelapplication.vm.characterdetails.usecase

interface CharacterDetailsUseCase {
    fun getCharacterDetailsFromServer(characterId: String):
        kotlinx.coroutines.flow.Flow<CharacterDetailsResult>
}