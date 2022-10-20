package com.example.marvelapplication.data.characters.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marvelapplication.data.characters.models.Character

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: List<Character>)

    @Update
    fun updateCharacter(character: Character)

    @Query("SELECT * FROM Characters ORDER BY modified DESC")
    fun readAllData(): LiveData<List<Character>>

    @Delete
    fun deleteCharacter(character: Character)
}