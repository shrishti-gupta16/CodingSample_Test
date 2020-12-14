package com.example.codingsample.data

import com.google.gson.Gson

class UserDataMapper {

    fun toUserDetail(user: User) : UserDetail {
        val gson = Gson()
        return UserDetail(
            username = user.username,
            password = user.password,
            gender = user.gender,
            name = gson.toJson(user.name),
            location =  gson.toJson(user.location),
            email = user.email,
            dob = user.dob,
            phone = user.phone,
            picture = user.picture
        )
    }

    fun toUser(user: UserDetail, isFavourite: Boolean) : User {
        val gson = Gson()
        return User(
            username = user.username,
            password = user.password,
            gender = user.gender,
            name = gson.fromJson(user.name, Name::class.java),
            location =  gson.fromJson(user.location, Location::class.java),
            email = user.email,
            dob = user.dob,
            phone = user.phone,
            picture = user.picture,
            isFavourite =  isFavourite
        )
    }

}