package com.example.codingsample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.databinding.LocationItemBinding

class LocationListAdapter(
    val onItemClicked: (location: LocationDetail, defaultSet: Boolean) -> Unit
) :
    ListAdapter<LocationDetail, LocationListAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LocationItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder constructor(private val binding: LocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationDetail) {
            binding.item = item
            binding.clParent.setOnClickListener {
                onItemClicked(item, false)
            }
//            binding.ivSetDefault.visibility = if (item.is_default != 1) View.VISIBLE else View.GONE
            binding.ivDelete.setOnClickListener {
                onItemClicked(item, true)
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<LocationDetail>() {
        override fun areItemsTheSame(oldItem: LocationDetail, newItem: LocationDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationDetail, newItem: LocationDetail): Boolean {
            return newItem == oldItem
        }
    }
}
