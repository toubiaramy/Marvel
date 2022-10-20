package com.example.marvelapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marvelapplication.data.characters.models.Character
import com.example.marvelapplication.data.characters.repository.CharactersDao

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
