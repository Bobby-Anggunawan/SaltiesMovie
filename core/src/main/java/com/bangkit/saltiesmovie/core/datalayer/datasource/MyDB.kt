package com.bangkit.saltiesmovie.core.datalayer.datasource

import androidx.room.*

object MyDB {
    @Entity
    data class Movie(
        @PrimaryKey val id: Int,
        val backdrop_path: String,
        val budget: Int,
        val homepage: String,
        val original_title: String,
        val overview: String,
        val poster_path: String,
        val release_date: String,
        val revenue: Int,
        val status: String,
        val tagline: String,
        val vote_average: Double,
        val vote_count: Int
    )

    @Dao
    interface MyDao {
        @Query("SELECT * from Movie limit 1 offset :page-1")
        fun getMoviePage(page: Int): Movie

        @Query("SELECT * FROM Movie WHERE id = :idMovie")
        fun loadById(idMovie: Int): Movie

        @Query("SELECT CASE WHEN EXISTS(Select * from Movie where id = :idMovie) then 1 else 0 end")
        fun isExist(idMovie: Int): Boolean

        @Query("SELECT count() from Movie")
        fun countData(): Int

        @Insert
        fun insertMovie(vararg data: Movie)

        @Delete
        fun deleteMovie(data: Movie)
    }

    @Database(entities = arrayOf(Movie::class), version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun saltiesDao(): MyDao
    }

}