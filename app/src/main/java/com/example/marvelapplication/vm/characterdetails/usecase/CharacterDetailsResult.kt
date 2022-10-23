package com.example.marvelapplication.vm.characterdetails.usecase

import com.example.marvelapplication.ui.characterdetails.CharacterDetails

sealed class CharacterDetailsResult {
    class Error(val msg: String) : CharacterDetailsResult()
    class Success(val data: List<CharacterDetails>) : CharacterDetailsResult()
    class NoData(val msg: String) : CharacterDetailsResult()
    object Loading : CharacterDetailsResult()
    object DismissLoading : CharacterDetailsResult()
}