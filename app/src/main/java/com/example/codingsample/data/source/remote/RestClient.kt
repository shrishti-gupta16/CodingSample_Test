// Copyright 2020 by Shrishti Gupta. All Rights Reserved.

package com.example.codingsample.data.source.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {

    private const val baseUrl = "http://api.openweathermap.org/"
    private var apiServices: ApiServices? = null

    val client: ApiServices
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okClient: OkHttpClient.Builder = OkHttpClient.Builder()
            okClient.connectTimeout(60, TimeUnit.SECONDS)
            okClient.readTimeout(60, TimeUnit.SECONDS)
            okClient.addInterceptor(logging)
            val client: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiServices = client.create(ApiServices::class.java)
            return apiServices!!
        }
}