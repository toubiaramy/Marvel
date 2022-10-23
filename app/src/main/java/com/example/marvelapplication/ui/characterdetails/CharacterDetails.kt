package com.example.marvelapplication.ui.characterdetails

import android.graphics.drawable.Drawable
import java.util.Date

interface CharacterDetails {
    fun id(): Int
    fun title(): String
    fun description(): String
    fun imageUrl(): String
    fun type(): CharacterDetailType
    fun date(): Date
    fun color(): Drawable?
}