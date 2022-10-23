package com.example.marvelapplication.data.comics

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.marvelapplication.MarvelApp
import com.example.marvelapplication.R
import com.example.marvelapplication.data.characters.models.Image
import com.example.marvelapplication.ui.characterdetails.CharacterDetailType
import com.example.marvelapplication.ui.characterdetails.CharacterDetails
import java.io.Serializable
import java.util.Date

class Series(
    val id: Int,
    val title: String,
    val description: String,
    val modified: Date,
    val thumbnail: Image,

) : Serializable, CharacterDetails {

    override fun id(): Int {
        return id
    }

    override fun title(): String {
        return title
    }

    override fun description(): String {
        return description
    }

    override fun imageUrl(): String {
        return thumbnail.path + "." + thumbnail.extension
    }

    override fun type(): CharacterDetailType {
        return CharacterDetailType.SERIES
    }

    override fun date(): Date {
        return modified
    }

    override fun color(): Drawable? {
        return ContextCompat.getDrawable(MarvelApp.context, R.color.black)
    }
}