package com.example.marvelapplication.vm.characters.usecase

import androidx.lifecycle.LiveData
import com.example.marvelapplication.data.characters.models.Character
import com.example.marvelapplication.data.characters.repository.CharacterRepository
import com.example.marvelapplication.retrofit.Api
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterUseCaseImpl @Inject constructor(
    private val api: Api,
    private val repository: CharacterRepository
) : CharacterUseCase {

    override var readDataFromLocalStorage: LiveData<List<Character>> =
        repository.readDataFromLocalStorage

    override suspend fun getCharacters(): Flow<CharactersResult> {
        return flow {
            emit(CharactersResult.Loading)
            val call = api.getCharacters().execute()
            if (call.isSuccessful) {
                emit(CharactersResult.Success(call.body()!!))
            } else {
                emit(CharactersResult.NoData)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertDataToLocalStorage(data: List<Character>) {
        repository.insertCharactersToLocalStorage(data)
    }

    override suspend fun deleteCharacter(character: Character) {
        repository.deleteCharacter(character)
    }
}