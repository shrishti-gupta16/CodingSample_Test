package com.example.codingsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "LocationDetail")
data class LocationDetail constructor(
    @ColumnInfo(name = "latitude") val latitude : Double,
    @ColumnInfo(name = "longitude") val longitude : Double,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "address") val address : String,
    @ColumnInfo(name = "is_default") var is_default : Int = 0,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
)