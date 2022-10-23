package com.example.marvelapplication.data.characters.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.marvelapplication.data.characters.models.Character
import com.example.marvelapplication.data.characters.models.Image
import com.example.marvelapplication.data.local.ApplicationDatabase
import com.example.marvelapplication.getOrAwaitValue
import com.google.common.truth.Truth
import java.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CharactersDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ApplicationDatabase
    private lateinit var charactersDao: CharactersDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ApplicationDatabase::class.java
        ).allowMainThreadQueries().build()
        charactersDao = database.charactersDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertCharacter() = runBlocking {
        val character = Character(
            1,
            "name1",
            "description1",
            Date(),
            "resourceUri1",
            Image("path1", ".ext")
        )
        charactersDao.insertCharacter(character)
        val dbCharacter = charactersDao.getAllCharacters().getOrAwaitValue()
        Truth.assertThat(dbCharacter).hasSize(1)
    }

    @Test
    fun insertCharacters() = runBlocking {
        charactersDao.insertCharacters(
            listOf(
                Character(
                    1,
                    "name1",
                    "description1",
                    Date(),
                    "resourceUri1",
                    Image("path1", ".ext")
                ),
                Character(
                    2,
                    "name2",
                    "description2",
                    Date(),
                    "resourceUri2",
                    Image("path2", ".ext")

                ),
                Character(
                    3,
                    "name3",
                    "description3",
                    Date(),
                    "resourceUri3",
                    Image("path3", ".ext")
                )
            )
        )
        val dbCharacter = charactersDao.getAllCharacters().getOrAwaitValue()
        Truth.assertThat(dbCharacter).hasSize(3)
    }

    @Test
    fun deleteCharacter() = runBlocking {
        val character = Character(
            1,
            "name1",
            "description1",
            Date(),
            "resourceUri1",
            Image("path1", ".ext")
        )
        charactersDao.insertCharacter(character)
        charactersDao.deleteCharacter(character)
        val dbCharacter = charactersDao.getAllCharacters().getOrAwaitValue()
        Truth.assertThat(dbCharacter).hasSize(0)
    }

    @Test
    fun updateCharacter() = runBlocking {
        val character = Character(
            1,
            "name1",
            "description1",
            Date(),
            "resourceUri1",
            Image("path1", ".ext")
        )
        charactersDao.insertCharacter(character)
        val newCharacter = Character(
            1,
            "name2",
            "description2",
            Date(),
            "resourceUri2",
            Image("path2", ".ext")
        )
        charactersDao.updateCharacter(newCharacter)
        val dbCharacter = charactersDao.getAllCharacters().getOrAwaitValue()
        Truth.assertThat(dbCharacter.get(0).name).isEqualTo("name2")
    }

    @Test
    fun insertCharacterIgnoreConflict() = runBlocking {
        val character = Character(
            1,
            "name1",
            "description1",
            Date(),
            "resourceUri1",
            Image("path1", ".ext")
        )
        charactersDao.insertCharacter(character)
        val newCharacter = Character(
            1,
            "name2",
            "description2",
            Date(),
            "resourceUri2",
            Image("path2", ".ext")
        )
        charactersDao.insertCharacter(newCharacter)
        val dbCharacter = charactersDao.getAllCharacters().getOrAwaitValue()
        Truth.assertThat(dbCharacter.get(0).name).isEqualTo("name1")
    }
}