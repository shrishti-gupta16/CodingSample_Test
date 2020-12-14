package com.example.codingsample.data

import com.google.gson.annotations.SerializedName

data class UserResult(
    @SerializedName("results") val results : List<Results>
)

data class Results (
    @SerializedName("user") val user : User,
    @SerializedName("seed") val seed : String,
    @SerializedName("version") val version : Double
)

data class User (
    @SerializedName("gender") val gender : String = "",
    @SerializedName("name") val name : Name = Name(),
    @SerializedName("location") val location : Location = Location(),
    @SerializedName("email") val email : String = "",
    @SerializedName("username") val username : String = "",
    @SerializedName("password") val password : String = "",
    @SerializedName("salt") val salt : String = "",
    @SerializedName("md5") val md5 : String = "",
    @SerializedName("sha1") val sha1 : String = "",
    @SerializedName("sha256") val sha256 : String = "",
    @SerializedName("registered") val registered : Int = 0,
    @SerializedName("dob") val dob : Int = 0,
    @SerializedName("phone") val phone : String = "",
    @SerializedName("cell") val cell : String = "",
    @SerializedName("SSN") val sSN : String = "",
    @SerializedName("picture") val picture : String = "",
    var isFavourite : Boolean = false
)

data class Name (
    @SerializedName("title") val title : String = "",
    @SerializedName("first") val first : String = "",
    @SerializedName("last") val last : String = ""
)

data class Location (
    @SerializedName("street") val street : String = "",
    @SerializedName("city") val city : String = "",
    @SerializedName("state") val state : String = "",
    @SerializedName("zip") val zip : Int = 0
)