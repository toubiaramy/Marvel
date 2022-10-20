package com.example.marvelapplication.data.characters

import java.io.Serializable

class EventList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: ArrayList<EventSummary>
) : Serializable