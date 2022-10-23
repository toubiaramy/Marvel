package com.example.marvelapplication.ui.characterdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.databinding.SingleRowCharacterDetailBinding

class CharacterDetailsAdapter :
    RecyclerView.Adapter<CharacterDetailsAdapter.CharacterDetailViewHolder>() {

    private var charList = emptyList<CharacterDetails>()

    private lateinit var binding: SingleRowCharacterDetailBinding

    class CharacterDetailViewHolder
    (private val binding: SingleRowCharacterDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(det: CharacterDetails) {
            binding.detail = det
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailViewHolder {
        binding =
            SingleRowCharacterDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CharacterDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterDetailViewHolder, position: Int) {
        val char = charList[position]
        holder.bind(char)
    }

    override fun getItemCount(): Int = charList.size

    private fun setData(newCharacterDetailsList: List<CharacterDetails>) {
        val diffUtil = CharacterDetailsDiffUtil(charList, newCharacterDetailsList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        charList = newCharacterDetailsList
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateData(newCharacterDetailsList: List<CharacterDetails>) {
        val newCombinedData: ArrayList<CharacterDetails> = ArrayList()
        newCombinedData.addAll(charList)
        newCombinedData.addAll(newCharacterDetailsList)
        setData(newCombinedData)
    }
}