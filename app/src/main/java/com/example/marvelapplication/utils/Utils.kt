package com.example.marvelapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.marvelapplication.R

class Utils {

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