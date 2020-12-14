package com.example.codingsample.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.codingsample.data.LocationDetail

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationDetail)

    @Query("SELECT EXISTS(SELECT * FROM LocationDetail WHERE latitude = :latitude AND longitude = :longitude)")
    suspend fun isLocationExist(latitude: Long, longitude: Long): Boolean

    @Delete
    suspend fun deleteLocation(location: LocationDetail)

    @Query("SELECT * FROM LocationDetail")
    fun observeLocationList(): LiveData<List<LocationDetail>>

    @Query("SELECT * FROM LocationDetail")
    fun getLocationList(): List<LocationDetail>

    @Query("SELECT * FROM LocationDetail WHERE name LIKE '%' || :name || '%' ")
    fun searchLocation(name: String): LiveData<List<LocationDetail>>

    @Query("SELECT * FROM LocationDetail WHERE id = :id")
    fun getLocationDetail(id: Int): LocationDetail
}