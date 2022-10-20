package com.example.marvelapplication.vm.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapplication.data.characters.models.Character
import com.example.marvelapplication.vm.characters.usecase.CharacterUseCase
import com.example.marvelapplication.vm.characters.usecase.CharactersResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val characterUseCase: CharacterUseCase
) : ViewModel() {

    val localData: LiveData<List<Character>> = characterUseCase.readDataFromLocalStorage
    val liveCharacters: MutableLiveData<CharactersResult>
        by lazy { MutableLiveData<CharactersResult>() }

    fun getCharactersFromServer() {
        viewModelScope.launch(dispatcher) {
            characterUseCase.getCharacters().catch {
                liveCharacters.postValue(CharactersResult.Error(it.message.toString()))
            }.collect {
                if (it is CharactersResult.Success) {
                    characterUseCase.insertDataToLocalStorage(it.data.data.results)
                }
                liveCharacters.postValue(it)
            }
        }
    }

    fun deleteCharacter(char: Character) {
        viewModelScope.launch(dispatcher) {
            characterUseCase.deleteCharacter(char)
        }
    }
}