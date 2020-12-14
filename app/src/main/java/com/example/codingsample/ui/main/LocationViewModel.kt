// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.data.repositroy.DefaultTasksRepository
import kotlinx.coroutines.launch


class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val tasksRepository = DefaultTasksRepository.getRepository(application)

    val searchQuery = MutableLiveData("")

    private val _locationListLiveData = searchQuery.distinctUntilChanged().map {
        if (it.isNullOrEmpty()) {
            tasksRepository.getLocationList()
        } else {
            tasksRepository.searchLocation(it)
        }
    }
    val locationListLiveData = _locationListLiveData


    fun addLocation(locationDetail: LocationDetail) {
        viewModelScope.launch {
            val result = tasksRepository.insertLocation(locationDetail)
        }
    }

    fun deleteLocation(locationDetail: LocationDetail) {
        viewModelScope.launch {
            val result = tasksRepository.deleteLocation(locationDetail)
        }
    }
}