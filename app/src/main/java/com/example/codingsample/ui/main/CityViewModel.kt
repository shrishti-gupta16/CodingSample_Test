// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.codingsample.data.ForecastItem
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.data.Result
import com.example.codingsample.data.WeatherForecast
import com.example.codingsample.data.repositroy.DefaultTasksRepository
import com.example.codingsample.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler


class CityViewModel(application: Application) : AndroidViewModel(application) {

    private val tasksRepository = DefaultTasksRepository.getRepository(application)

    var cityId = -1
    val dataLoading = MutableLiveData<Boolean>()
    val isNetworkAvailable = MutableLiveData<Boolean>()

    val weatherForecastListLiveData = MutableLiveData<List<ForecastItem>>()
    lateinit var locationDetail: LocationDetail

    val selectedWeatherForecastLiveData = MutableLiveData<ForecastItem>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            locationDetail = tasksRepository.getLocationDetail(cityId)
        }
    }

    fun loadWeatherForecast() {
        viewModelScope.launch {
            if (::locationDetail.isInitialized.not()) {
                loadWeatherForecast()
                return@launch
            }
            if (isNetworkAvailable.value!!.not()) {
                return@launch
            }

            dataLoading.value = true
            viewModelScope.launch {
                val result = tasksRepository.getWeatherForecast(locationDetail.latitude, locationDetail.longitude)
                dataLoading.value = false
                if (result != null) {
                    val weatherForecastList : ArrayList<ForecastItem> = ArrayList()
                    if (result is Result.Success) {
                        result.data.let {
                            if ( it.forecastList.isNotEmpty()) {
                                val map = it.forecastList.groupBy { Utils.formatDate(it.dateValue) }
                                map.forEach{ entry ->
                                    weatherForecastList.add(ForecastItem(entry.key, entry.value))
                                }
                            }
                            selectedWeatherForecastLiveData.value = weatherForecastList[0]
                        }
                    } else if (result is Result.Error) {
                        weatherForecastList.clear()
                    }
                    weatherForecastListLiveData.value = weatherForecastList
                }
            }

        }
    }

}