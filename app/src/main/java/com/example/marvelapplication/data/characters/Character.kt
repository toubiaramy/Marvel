package com.example.marvelapplication.data.characters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.marvelapplication.R
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