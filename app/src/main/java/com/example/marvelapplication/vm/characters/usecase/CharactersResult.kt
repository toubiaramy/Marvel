package com.example.marvelapplication.vm.characters.usecase

import com.example.marvelapplication.data.characters.CharacterDataWrapper

sealed class CharactersResult {
    class Error(val msg: String) : CharactersResult()
    class Success(val data: CharacterDataWrapper) : CharactersResult()
    object NoData : CharactersResult()
    object Loading : CharactersResult()
}