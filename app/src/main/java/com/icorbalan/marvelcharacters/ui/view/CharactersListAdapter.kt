package com.icorbalan.marvelcharacters.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icorbalan.marvelcharacters.R
import com.icorbalan.marvelcharacters.data.model.CharacterModel
import com.icorbalan.marvelcharacters.databinding.ItemCharacterListBinding

class CharactersListAdapter: ListAdapter<CharacterModel, CharactersListAdapter.CharacterViewHolder>(DiffCallBack) {

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCharacterListBinding.bind(view)

        fun bin(character: CharacterModel) {
            binding.tvName.text = character.name
            Glide.with(binding.imCharacterImage.context).load(character.thumbnail.imageUrl()).into(binding.imCharacterImage)
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_character_list, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bin(character)
    }
}