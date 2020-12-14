package com.example.codingsample.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.codingsample.R
import com.example.codingsample.data.ForecastItem
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.data.User
import com.example.codingsample.data.WeatherForecast
import com.example.codingsample.ui.main.LocationListAdapter
import com.example.codingsample.ui.main.WeatherForecastAdapter
import com.example.codingsample.ui.main.WeatherForecastDayAdapter


@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<LocationDetail>?) {
    items?.let {
        (listView.adapter as LocationListAdapter).submitList(items.toMutableList())
    }
}

@BindingAdapter("app:items")
fun setWeatherForecastItems(listView: RecyclerView, items: List<ForecastItem>?) {
    items?.let {
        (listView.adapter as WeatherForecastAdapter).submitList(items.toMutableList())

    }
}

@BindingAdapter("app:items")
fun setForecastDayItems(listView: RecyclerView, items: List<WeatherForecast>?) {
    items?.let {
        (listView.adapter as WeatherForecastDayAdapter).submitList(items.toMutableList())

    }
}

@BindingAdapter("app:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView);
    }
}

@BindingAdapter("app:formatDate")
fun formatDate(textView: TextView, date: String) {
    textView.text = String.format(textView.context.getString(R.string.time_value), Utils.formatDateTime(date))
}