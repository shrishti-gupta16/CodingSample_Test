package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import com.example.codingsample.data.LocationDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationLocalDataSourceImpl(private val locationDao: LocationDao) : LocationLocalDataSource {

    override suspend fun insertLocation(locationDetail: LocationDetail) =
        withContext(Dispatchers.IO) {
            locationDao.insertLocation(locationDetail)
        }

    override suspend fun isLocationExist(latitude: Long, longitude: Long) =
        withContext(Dispatchers.IO) {
            locationDao.isLocationExist(latitude, longitude)
        }

    override suspend fun deleteLocation(locationDetail: LocationDetail) =
        withContext(Dispatchers.IO) {
            locationDao.deleteLocation(locationDetail)
        }

    override fun getLocationList(): LiveData<List<LocationDetail>> {
        return locationDao.observeLocationList()
    }

    override fun searchLocation(query: String): LiveData<List<LocationDetail>> = locationDao.searchLocation(query)

    override fun getLocationDetail(cityId: Int) = locationDao.getLocationDetail(cityId)
}