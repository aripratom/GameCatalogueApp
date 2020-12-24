package com.aripratom.core.utils

import androidx.room.TypeConverter
import com.aripratom.core.domain.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun genreStringToList(source: String?): List<Genre>? {
        if (source == null) return null
        return Gson().fromJson(source, object : TypeToken<List<Genre>?>() {}.type)
    }

    @TypeConverter
    fun genreListToString(source: List<Genre>?): String? {
        if (source == null) return null
        return Gson().toJson(source)
    }


}