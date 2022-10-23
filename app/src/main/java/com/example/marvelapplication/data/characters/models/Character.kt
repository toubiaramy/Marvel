package com.example.marvelapplication.data.characters.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "Characters")
class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    // val urls: ArrayList<Url>,
    @Embedded
    val thumbnail: Image,
    // val comics: ComicList,
    // val stories: StoryList,
    // val events: EventList,
    // val series: SeriesList
) : Serializable {

    fun imageUrl(): String {
        return thumbnail.path + "." + thumbnail.extension
    }
}