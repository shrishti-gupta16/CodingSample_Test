package com.example.codingsample.data.source.remote

import com.example.codingsample.data.Result
import com.example.codingsample.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRemoteDataSourceImpl(private val service: ApiServices) : LocationRemoteDataSource {

    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ): Result<WeatherResponse> = withContext(Dispatchers.IO) {
        try {
            val call = service.getWeatherForecastAsync(latitude, longitude)
            val response = call.await()
            Result.Success(response.body()!!)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}