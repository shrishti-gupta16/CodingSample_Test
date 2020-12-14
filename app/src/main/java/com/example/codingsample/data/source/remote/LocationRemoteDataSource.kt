package com.example.codingsample.data.source.remote

import com.example.codingsample.data.UserResult
import com.example.codingsample.data.Result
import com.example.codingsample.data.WeatherResponse


interface LocationRemoteDataSource {

    suspend fun getWeatherForecast(latitude: Double, longitude: Double) : Result<WeatherResponse>
}