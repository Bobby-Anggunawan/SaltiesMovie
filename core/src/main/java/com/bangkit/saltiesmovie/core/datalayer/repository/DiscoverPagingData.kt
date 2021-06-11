package com.bangkit.saltiesmovie.core.datalayer.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bangkit.saltiesmovie.core.datalayer.datasource.RemoteDataSource
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import com.bangkit.saltiesmovie.core.util.JsonMapper
import com.bangkit.saltiesmovie.core.util.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Suppress("BlockingMethodInNonBlockingContext")
class DiscoverPagingData(val remote: RemoteDataSource): PagingSource<Int, MoviePageDomainMod>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, MoviePageDomainMod> {
        try {
            val thisPage = params.key ?: 1
            val response: ArrayList<MoviePageDomainMod> = withContext(Dispatchers.IO){
                val dataFromApi = JsonMapper.toMoviePage(remote.getDiscoverPage(thisPage))
                Log.e("myerr", "jalan 1 $dataFromApi")
                ObjectMapper.MoviePageDataMod_MoviePageDomainMod(dataFromApi)
            }

            Log.e("myerr", "jalan 2 $response")

            return LoadResult.Page(
                data = response ,
                prevKey = if(thisPage > 1) thisPage-1 else null,
                nextKey = if(thisPage < 580105) thisPage+1 else null
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