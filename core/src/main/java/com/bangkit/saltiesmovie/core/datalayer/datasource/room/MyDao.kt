package com.bangkit.saltiesmovie.core.datalayer.datasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bangkit.saltiesmovie.core.datalayer.model.Movie

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