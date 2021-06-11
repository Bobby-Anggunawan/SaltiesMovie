package com.bangkit.saltiesmovie.core.datalayer.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bangkit.saltiesmovie.core.datalayer.datasource.MyDB
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import com.bangkit.saltiesmovie.core.util.JsonMapper
import com.bangkit.saltiesmovie.core.util.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("BlockingMethodInNonBlockingContext")
class FavoritePagingSource(val db: MyDB.MyDao) : PagingSource<Int, MoviePageDomainMod>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MoviePageDomainMod> {
        try {
            val thisPage = params.key ?: 1
            var countDB = 0
            val response: ArrayList<MoviePageDomainMod> = withContext(Dispatchers.IO){
                val dataFromApi = db.getMoviePage(thisPage)
                Log.e("myerr", "jalan 1 $dataFromApi")
                countDB = db.countData()
                arrayListOf(ObjectMapper.MovieDB_MoviePageDomainMod(dataFromApi))
            }

            Log.e("myerr", "jalan 2 $response")

            return LoadResult.Page(
                data = response ,
                prevKey = if(thisPage > 1) thisPage-1 else null,
                nextKey = if(thisPage < countDB) thisPage+1 else null
            )
        } catch (e: Exception) {
            return  LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviePageDomainMod>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}