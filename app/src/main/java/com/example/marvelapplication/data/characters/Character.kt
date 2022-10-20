package com.example.marvelapplication.data.characters

import java.io.Serializable
import java.util.Date
import kotlin.collections.ArrayList

class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: Date,
    val resourceURI: String,
    val urls: ArrayList<Url>,
    val thumbnail: Image,
    val comics: ComicList,
    val stories: StoryList,
    val events: EventList,
    val series: SeriesList
) : Serializable