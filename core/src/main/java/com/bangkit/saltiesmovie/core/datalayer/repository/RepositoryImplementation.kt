package com.bangkit.saltiesmovie.core.datalayer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bangkit.saltiesmovie.core.datalayer.datasource.RemoteDataSource
import com.bangkit.saltiesmovie.core.datalayer.datasource.room.MyDao
import com.bangkit.saltiesmovie.core.datalayer.model.MovieDetailDataMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import com.bangkit.saltiesmovie.core.util.JsonMapper
import com.bangkit.saltiesmovie.core.util.ObjectMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImplementation constructor(val remote: RemoteDataSource, val discoverPS: DiscoverPagingData, val favoritePS: FavoritePagingSource, val db: MyDao): SaltiesRepository {
    override fun getDiscoverPagingData(): Flow<PagingData<MoviePageDomainMod>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            discoverPS
        }.flow
    }

    override fun getFavoritePagingData(): Flow<PagingData<MoviePageDomainMod>> {
        return Pager(
            PagingConfig(pageSize = 1)
        ) {
            favoritePS
        }.flow
    }

    override suspend fun getOnlineInfo(id: Int): Flow<MovieDetailDomainMod> {
        return flow{
            var getFromServer: MovieDetailDataMod
            withContext(Dispatchers.IO){
                getFromServer = JsonMapper.toMovieDetail(remote.getMovieDetail(id))
            }
            emit(ObjectMapper.convert(getFromServer))
        }
    }

    override fun addToDatabase(data: MovieDetailDomainMod) {
        Thread(
            {
                db.insertMovie(ObjectMapper.MovieDetailDomainMod_MovieDB(data))
            }
        ).start()
    }

    override fun removeFromDatabase(data: MovieDetailDomainMod) {
        Thread(
            {
                db.deleteMovie(ObjectMapper.MovieDetailDomainMod_MovieDB(data))
            }
        ).start()
    }

    override fun isExist(id: Int): Boolean {
        var ret: Boolean
        runBlocking{
            val waitData = async(context = Dispatchers.IO){db.isExist(id)}
            ret = waitData.await()
        }
        return ret
    }
}