package com.davoh.starwarsapi_mvvm.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.databinding.RowPlanetBinding
import com.davoh.starwarsapi_mvvm.domain.Planet

class PlanetListAdapter : ListAdapter<Planet, RecyclerView.ViewHolder>(DiffCallback())  {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_planet,parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: RowPlanetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Planet) {
            binding.planet = item
            binding.row.setOnClickListener {
                listener?.onFavoriteBtnClick(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onFavoriteBtnClick(planet: Planet)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<Planet>() {
        override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
            return oldItem == newItem
        }
    }

}

