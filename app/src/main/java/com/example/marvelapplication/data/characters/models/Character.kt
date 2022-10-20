package com.example.marvelapplication.data.characters.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.example.marvelapplication.R
import java.io.Serializable
import java.util.Date

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
        return thumbnail.path.replace("http", "https") + "." + thumbnail.extension
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageBinding")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(view)
        }
    }
}