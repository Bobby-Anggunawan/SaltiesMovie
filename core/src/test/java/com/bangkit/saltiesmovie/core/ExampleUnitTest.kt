package com.bangkit.saltiesmovie.core

import com.bangkit.saltiesmovie.core.datalayer.datasource.RemoteDataSource
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        println(RemoteDataSource().getMovieDetail(337404))
    }
}