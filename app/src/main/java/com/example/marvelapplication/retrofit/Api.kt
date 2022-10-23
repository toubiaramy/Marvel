package com.example.marvelapplication.retrofit

import com.example.marvelapplication.data.characters.models.CharacterDataWrapper
import com.example.marvelapplication.data.comics.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("v1/public/characters")
    fun getCharacters(): Call<CharacterDataWrapper>

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int
    ): Call<ComicDataWrapper>

    @GET("v1/public/characters/{characterId}/events")
    fun getEvents(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int
    ): Call<EventDataWrapper>

    @GET("v1/public/characters/{characterId}/series")
    fun getSeries(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int
    ): Call<SeriesDataWrapper>

    @GET("v1/public/characters/{characterId}/stories")
    fun getStories(
        @Path("characterId") characterId: String,
        @Query("limit") limit: Int
    ): Call<StoryDataWrapper>
}