package com.example.codingsample

import com.example.codingsample.data.source.remote.ApiServices
import com.example.codingsample.data.source.remote.LocationRemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LocationRemoteDataSourceTest {

    private lateinit var userRemoteDataSourceImpl: LocationRemoteDataSourceImpl
    private lateinit var apiServices: ApiServices

    @Before
    fun setUp() {
        apiServices = mock(ApiServices::class.java)
        userRemoteDataSourceImpl = LocationRemoteDataSourceImpl(apiServices)
    }

}