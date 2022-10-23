package com.example.marvelapplication.vm.characterdetails

import androidx.lifecycle.ViewModel
import com.example.marvelapplication.vm.characterdetails.usecase.CharacterDetailsResult
import com.example.marvelapplication.vm.characterdetails.usecase.CharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * This is the CharacterDetailsFragment view model
 * It depends on characterDetailsUseCase
 * It invoke the use case getCharacterDetailsFromServer to retrieve the data from the server
 * It passes the flow to the fragment to be collected and update the UI accordingly to the call result
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsUseCase: CharacterDetailsUseCase
) : ViewModel() {

    /**
     * Method called from the fragment once created
     * It invoke the use case to retrieve character details from server
     * It returns a flow that is consumed in CharacterDetailsFragment to update the UI
     */
    fun getFlowDataFromServer(characterId: String): Flow<CharacterDetailsResult> {
        return characterDetailsUseCase.getCharacterDetailsFromServer(characterId)
    }
}