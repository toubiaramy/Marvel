package com.example.marvelapplication.retrofit

import com.example.marvelapplication.data.characters.models.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("v1/public/characters")
    fun getCharacters(): Call<CharacterDataWrapper>
}