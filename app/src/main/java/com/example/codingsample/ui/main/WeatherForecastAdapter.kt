package com.example.codingsample.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codingsample.data.ForecastItem
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.data.WeatherForecast
import com.example.codingsample.databinding.WeatherForecastItemBinding

class WeatherForecastAdapter(val onItemClicked: (forecastItem: ForecastItem) -> Unit) : ListAdapter<ForecastItem, WeatherForecastAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WeatherForecastItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    inner class ViewHolder constructor(val binding: WeatherForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastItem) {
            binding.item = item
            binding.cardView.setOnClickListener {
                onItemClicked(item)
            }
            binding.executePendingBindings()
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<ForecastItem>() {
        override fun areItemsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: ForecastItem, newItem: ForecastItem): Boolean {
            return oldItem == newItem
        }
    }

}
