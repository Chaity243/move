package com.chaity.easymove.data.database

import android.arch.persistence.room.TypeConverter
import com.chaity.easymove.domain.entity.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DbTypeConverters {

    @TypeConverter
    fun toLocObject(value: String): Location {
        val listType = object : TypeToken<Location>() {

        }.type
        return Gson().fromJson<Location>(value, listType)
    }

    @TypeConverter
    fun fromLocationObject(location: Location): String {
        val gson = Gson()
        return gson.toJson(location)
    }
}