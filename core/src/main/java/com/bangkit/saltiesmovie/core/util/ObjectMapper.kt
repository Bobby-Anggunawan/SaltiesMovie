package com.bangkit.saltiesmovie.core.util

import com.bangkit.saltiesmovie.core.datalayer.datasource.MyDB
import com.bangkit.saltiesmovie.core.datalayer.model.MovieDetailDataMod
import com.bangkit.saltiesmovie.core.datalayer.model.MoviePageDataMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import kotlin.reflect.typeOf


object ObjectMapper {

    inline fun <reified T> checkType() = when (T::class) {
        MovieDetailDataMod::class -> "MovieDetailDataMod"
        MovieDetailDomainMod::class -> "MovieDetailDomainMod"
        MoviePageDataMod::class -> "MoviePageDataMod"
        ArrayList::class -> "ArrayList"
        else -> "---"
    }


    inline fun <reified T, reified U> convert(data: T): U {
        val dataType = checkType<T>()
        val returnType = checkType<U>()
        if(dataType == "MovieDetailDataMod" && returnType == "MovieDetailDomainMod"){
            data as MovieDetailDataMod
            return MovieDetail_DataToDomain(data) as U
        }
        else if(dataType == "MoviePageDataMod" && returnType == "ArrayList"){
            data as MoviePageDataMod
            return MoviePageDataMod_MoviePageDomainMod(data) as U
        }
        else{
            throw NoSuchMethodException("belum diimplementasikan ke converter")
        }
    }

    fun MovieDetail_DataToDomain(source: MovieDetailDataMod): MovieDetailDomainMod{
        return MovieDetailDomainMod(source.backdrop_path ,source.budget, source.homepage, source.id, source.original_title, source.overview, source.poster_path, source.release_date, source.revenue, source.status, source.tagline, source.vote_average, source.vote_count)
    }

    fun MoviePageDataMod_MoviePageDomainMod(source: MoviePageDataMod): ArrayList<MoviePageDomainMod>{
        val alist: ArrayList<MoviePageDomainMod> = arrayListOf()
        source.results.forEach {
            alist.add(MoviePageDomainMod(it.adult, it.backdrop_path, it.genre_ids, it.id, it.original_language, it.original_title, it.overview, it.popularity, it.poster_path, it.release_date, it.original_title, it.video, it.vote_average, it.vote_count))
        }
        return alist
    }

    fun MovieDetailDomainMod_MovieDB(source: MovieDetailDomainMod): MyDB.Movie{
        return MyDB.Movie(source.id, source.backdrop_path, source.budget, source.homepage, source.original_title, source.overview, source.poster_path, source.release_date, source.revenue, source.status, source.tagline, source.vote_average, source.vote_count)
    }

    fun MovieDB_MoviePageDomainMod(source: MyDB.Movie): MoviePageDomainMod{
        return MoviePageDomainMod(false, source.backdrop_path, arrayListOf(), source.id, "En", source.original_title, source.overview, 1.1, source.poster_path, source.release_date, source.original_title, false, source.vote_average, source.vote_count)
    }
}