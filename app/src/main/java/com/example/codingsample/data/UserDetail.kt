package com.example.codingsample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserDetail")
class UserDetail constructor(
    @PrimaryKey @ColumnInfo(name = "username") val username : String,
    @ColumnInfo(name = "password") val password : String,
    @ColumnInfo(name = "gender") val gender : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "location") val location : String,
    @ColumnInfo(name = "email") val email : String,
    @ColumnInfo(name = "dob") val dob : Int,
    @ColumnInfo(name = "phone") val phone : String,
    @ColumnInfo(name = "picture") val picture : String
)