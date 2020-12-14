package com.example.codingsample

import com.example.codingsample.data.UserDetail
import com.example.codingsample.data.source.local.LocationDao
import com.example.codingsample.data.source.local.LocationLocalDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class UserLocalDataSourceTest {

    private lateinit var locationDao: LocationDao
    private lateinit var userLocalDataSourceImpl: LocationLocalDataSourceImpl

    @Before
    fun setUp() {
        locationDao = mock(LocationDao::class.java)
        userLocalDataSourceImpl = LocationLocalDataSourceImpl(locationDao)
    }

}