package com.example.marvelapplication.vm.characterdetails.usecase

import android.util.Log
import com.example.marvelapplication.retrofit.Api
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * This is a use case class to retrieve character comics, events, stories and series from server
 * This class depends on Api class that makes the call to server using retrofit
 * This is the implementation class of CharacterDetailsUseCase interface
 * We only retrieve 3 objects from the server for each one of the above
 * This class returns a flow
 * In this flow data are retrieved synchronously
 * Data are retrieved based on character Id
 * If any exception occurred in any call, this exception is caught in try catch block in order to keep the flow process running
 * Once call is done we check the call status if successful or not and emmit data accordingly
 * If the call is successful then we check the data received from server if it is empty or not
 * If empty we emmit no data
 * If data exists we emmit it as is to ui
 * Logs are available for monitoring each step in the flow while application is running
 */
class CharacterDetailsUseCaseImpl @Inject constructor(
    private val api: Api
) : CharacterDetailsUseCase {
    private val logTag: String? = CharacterDetailsUseCaseImpl::class.simpleName
    private val limit = 3
    override fun getCharacterDetailsFromServer(characterId: String): Flow<CharacterDetailsResult> {
        return flow {

            emit(CharacterDetailsResult.Loading)
            try {
                Log.d(logTag, "comicCall called")
                val comicCall = api.getComics(characterId, limit).execute()
                if (comicCall.isSuccessful) {
                    Log.d(logTag, "comicCall success")
                    val data = comicCall.body()!!.data.results
                    if (data.size == 0) {
                        Log.d(logTag, "comicCall success no data")
                        emit(CharacterDetailsResult.NoData("No Comics received"))
                    } else {
                        Log.d(logTag, "comicCall success data size:" + data.size)
                        emit(CharacterDetailsResult.Success(data))
                    }
                } else {
                    Log.d(logTag, "comicCall fails")
                    emit(CharacterDetailsResult.NoData("Call Error: No Comics received"))
                }
            } catch (e: Exception) {
                emit(CharacterDetailsResult.Error("Error: Fail to retrieve comics:\n" + e.cause))
            }

            emit(CharacterDetailsResult.Loading)
            try {
                Log.d(logTag, "eventCall called")
                val eventCall = api.getEvents(characterId, limit).execute()
                if (eventCall.isSuccessful) {
                    Log.d(logTag, "eventCall success")
                    val data = eventCall.body()!!.data.results
                    if (data.size == 0) {
                        Log.d(logTag, "eventCall success no data")
                        emit(CharacterDetailsResult.NoData("No Events received"))
                    } else {
                        Log.d(logTag, "eventCall success data size:" + data.size)
                        emit(CharacterDetailsResult.Success(data))
                    }
                } else {
                    Log.d(logTag, "eventCall fails")
                    emit(CharacterDetailsResult.NoData("Call Error: No Events received"))
                }
            } catch (e: Exception) {
                emit(CharacterDetailsResult.Error("Error: Fail to retrieve events:\n" + e.cause))
            }

            emit(CharacterDetailsResult.Loading)
            try {
                Log.d(logTag, "storyCall called")
                val storyCall = api.getStories(characterId, limit).execute()
                if (storyCall.isSuccessful) {
                    Log.d(logTag, "storyCall success")
                    val data = storyCall.body()!!.data.results
                    if (data.size == 0) {
                        Log.d(logTag, "storyCall success no data")
                        emit(CharacterDetailsResult.NoData("No Stories received"))
                    } else {
                        Log.d(logTag, "storyCall success data size:" + data.size)
                        emit(CharacterDetailsResult.Success(data))
                    }
                } else {
                    Log.d(logTag, "storyCall fails")
                    emit(CharacterDetailsResult.NoData("Call Error: No Stories received"))
                }
            } catch (e: Exception) {
                emit(CharacterDetailsResult.Error("Error: Fail to retrieve stories:\n" + e.cause))
            }

            emit(CharacterDetailsResult.Loading)
            try {
                Log.d(logTag, "seriesCall called")
                val seriesCall = api.getSeries(characterId, limit).execute()
                if (seriesCall.isSuccessful) {
                    Log.d(logTag, "seriesCall success")
                    val data = seriesCall.body()!!.data.results
                    if (data.size == 0) {
                        Log.d(logTag, "seriesCall success no data")
                        emit(CharacterDetailsResult.NoData("No Series received"))
                    } else {
                        Log.d(logTag, "seriesCall success data size:" + data.size)
                        emit(CharacterDetailsResult.Success(data))
                    }
                } else {
                    Log.d(logTag, "seriesCall fails")
                    emit(CharacterDetailsResult.NoData("Call Error: No Series received"))
                }
            } catch (e: Exception) {
                emit(CharacterDetailsResult.Error("Error: Fail to retrieve series:\n" + e.cause))
            }

            emit(CharacterDetailsResult.DismissLoading)
        }.flowOn(Dispatchers.IO)
    }
}