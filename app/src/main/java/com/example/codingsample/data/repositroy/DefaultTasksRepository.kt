package com.example.codingsample.data.repositroy

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Room
import com.example.codingsample.data.*

import com.example.codingsample.data.source.local.*
import com.example.codingsample.data.source.remote.RestClient
import com.example.codingsample.data.source.remote.LocationRemoteDataSource
import com.example.codingsample.data.source.remote.LocationRemoteDataSourceImpl

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 */
class DefaultTasksRepository private constructor(application: Application) {

    private val locationRemoteDataSource: LocationRemoteDataSource
    private val locationLocalDataSource: LocationLocalDataSource

    companion object {
        @Volatile
        private var INSTANCE: DefaultTasksRepository? = null

        fun getRepository(app: Application): DefaultTasksRepository {
            return INSTANCE
                ?: synchronized(this) {
                DefaultTasksRepository(
                    app
                ).also {
                    INSTANCE = it
                }
            }
        }
    }

    init {
        val database = Room.databaseBuilder(
            application.applicationContext,
            LocationDatabase::class.java, "Users.db"
        ).build()

        locationLocalDataSource = LocationLocalDataSourceImpl(database.locationDao())
        locationRemoteDataSource = LocationRemoteDataSourceImpl(RestClient.client)
    }

    suspend fun insertLocation(locationDetail: LocationDetail) {
        locationLocalDataSource.insertLocation(locationDetail)
    }

    suspend fun isLocationExist(latitude: Long, longitude: Long) : Boolean {
        return locationLocalDataSource.isLocationExist(latitude, longitude)
    }

    suspend fun deleteLocation(locationDetail: LocationDetail) {
        locationLocalDataSource.deleteLocation(locationDetail)
    }

    fun getLocationList() : LiveData<List<LocationDetail>> {
        return locationLocalDataSource.getLocationList()
    }

    suspend fun getWeatherForecast(latitude: Double, longitude: Double) : Result<WeatherResponse> {
        return locationRemoteDataSource.getWeatherForecast(latitude, longitude)
    }

    fun searchLocation(query: String) : LiveData<List<LocationDetail>> {
        return locationLocalDataSource.searchLocation(query)
    }

    fun getLocationDetail(cityId: Int) : LocationDetail {
        return locationLocalDataSource.getLocationDetail(cityId)
    }
}

