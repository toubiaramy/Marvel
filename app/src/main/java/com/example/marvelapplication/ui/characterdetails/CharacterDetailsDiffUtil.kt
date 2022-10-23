package com.example.marvelapplication.ui.characterdetails

import androidx.recyclerview.widget.DiffUtil

class CharacterDetailsDiffUtil(
    private val oldList: List<CharacterDetails>,
    private val newList: List<CharacterDetails>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id() == newList[newItemPosition].id()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id() != newList[newItemPosition].id() -> {
                false
            }
            oldList[oldItemPosition].title() != newList[newItemPosition].title() -> {
                false
            }
            else -> true
        }
    }
}