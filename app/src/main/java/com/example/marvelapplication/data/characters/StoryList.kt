package com.example.marvelapplication.data.characters

import java.io.Serializable

class StoryList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<StorySummary>
) : Serializable