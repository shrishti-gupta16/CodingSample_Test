// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.data.source.remote

import com.example.codingsample.data.WeatherResponse
import com.example.codingsample.utils.Utils
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/data/2.5/forecast?appid=${Utils.API_URL}")
    fun getWeatherForecastAsync(@Query("lat") latitude: Double,
                           @Query("lon") longitude: Double): Deferred<Response<WeatherResponse>>
}