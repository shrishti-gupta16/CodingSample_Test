package com.example.codingsample.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city") val city: City,
    @SerializedName("cod") val code: String,
    @SerializedName("list") val forecastList: List<WeatherForecast>,
    @SerializedName("message") val message: Int
)

data class City(
    @SerializedName("country") val country: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("population")val population: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
    @SerializedName("timezone") val timezone: Int
)

data class WeatherForecast(
    @SerializedName("dt") val date: Int,
    @SerializedName("dt_txt") val dateValue: String,
    @SerializedName("main") val temperature: Temperature,
    @SerializedName("rain") val rain: Rain,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind
)

data class Temperature(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("grnd_level") val grndLevel: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_kf") val tempKf: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double
)

data class Rain(
    @SerializedName("3h") val value : Double
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val name: String
)

data class Wind(
    @SerializedName("deg") val deg: Int,
    @SerializedName("speed") val speed: Double
)