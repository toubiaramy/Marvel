package com.example.marvelapplication.data.characters

import java.io.Serializable

class ComicList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<ComicSummary>
) : Serializable