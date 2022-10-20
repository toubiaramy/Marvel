package com.example.marvelapplication.vm.characters.usecase

import com.example.marvelapplication.retrofit.Api
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharacterUseCaseImpl @Inject constructor(
    private val api: Api
) : CharacterUseCase {

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
}