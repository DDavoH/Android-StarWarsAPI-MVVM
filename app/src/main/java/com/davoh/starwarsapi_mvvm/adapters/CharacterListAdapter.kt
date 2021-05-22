package com.davoh.starwarsapi_mvvm.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.databinding.RowCharacterBinding
import com.davoh.starwarsapi_mvvm.domain.Character

class CharacterListAdapter : ListAdapter<Character, RecyclerView.ViewHolder>(DiffCallback())  {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: RowCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) {
           binding.character = item
           binding.row.setOnClickListener {
                listener?.onFavoriteBtnClick(item)
           }
        }
    }

    interface OnItemClickListener {
        fun onFavoriteBtnClick(character: Character)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}

