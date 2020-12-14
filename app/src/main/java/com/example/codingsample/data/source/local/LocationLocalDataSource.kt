package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.data.Result
import com.example.codingsample.data.UserDetail

interface LocationLocalDataSource {
    suspend fun insertLocation(locationDetail: LocationDetail)

    suspend fun isLocationExist(latitude: Long, longitude : Long) : Boolean

    suspend fun deleteLocation(locationDetail: LocationDetail)

    fun getLocationList(): LiveData<List<LocationDetail>>

    fun searchLocation(query: String): LiveData<List<LocationDetail>>

    fun getLocationDetail(cityId: Int): LocationDetail

}