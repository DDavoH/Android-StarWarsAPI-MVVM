package com.davoh.starwarsapi_mvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davoh.starwarsapi_mvvm.R
import com.davoh.starwarsapi_mvvm.databinding.RowVehicleBinding
import com.davoh.starwarsapi_mvvm.domain.Vehicle

class VehicleListAdapter : ListAdapter<Vehicle, RecyclerView.ViewHolder>(DiffCallback())  {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_vehicle,parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: RowVehicleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Vehicle) {
            binding.vehicle = item
        }
    }

    interface OnItemClickListener {
        fun onFavoriteBtnClick(vehicle: Vehicle)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }
    }
}