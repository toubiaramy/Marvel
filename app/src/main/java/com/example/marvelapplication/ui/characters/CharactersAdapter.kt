package com.example.marvelapplication.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.data.characters.models.Character
import com.example.marvelapplication.databinding.SingleRowCharacterBinding

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private var charList = emptyList<Character>()

    private lateinit var binding: SingleRowCharacterBinding

    class CharactersViewHolder
    (private val binding: SingleRowCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(char: Character) {
            binding.character = char
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        binding =
            SingleRowCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val char = charList[position]
        holder.bind(char)
        holder.itemView.setOnClickListener {
            /*holder.itemView.findNavController().navigate(
                UsersListFragmentDirections.fromUserListToAddUser().setUser(user)
            )*/
        }
    }

    override fun getItemCount(): Int = charList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(chars: List<Character>) {
        charList = chars
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Character {
        return charList[position]
    }
}