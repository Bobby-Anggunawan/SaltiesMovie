package com.bangkit.saltiesmovie.core.util

import com.bangkit.saltiesmovie.core.datalayer.model.MovieDetailDataMod
import com.bangkit.saltiesmovie.core.datalayer.model.MoviePageDataMod
import com.google.gson.Gson
import org.koin.java.KoinJavaComponent.inject

object JsonMapper {
    val gson: Gson = FieldInjection.injectField()

    fun toMovieDetail(jsonString: String): MovieDetailDataMod{
        val sg = Gson()
        return sg.fromJson(jsonString, MovieDetailDataMod::class.java)
    }

    fun toMoviePage(jsonString: String): MoviePageDataMod{
        val sg = Gson()
        return sg.fromJson(jsonString, MoviePageDataMod::class.java)
    }
}