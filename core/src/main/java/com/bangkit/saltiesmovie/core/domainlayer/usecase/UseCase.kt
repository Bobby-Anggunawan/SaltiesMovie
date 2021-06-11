package com.bangkit.saltiesmovie.core.domainlayer.usecase

import androidx.paging.PagingData
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getDiscoverPaging(): Flow<PagingData<MoviePageDomainMod>>

    fun getFavoritePaging(): Flow<PagingData<MoviePageDomainMod>>

    suspend fun getInfo(id: Int): Flow<MovieDetailDomainMod>

    fun addToDB(data: MovieDetailDomainMod)

    fun removeFromDB(data: MovieDetailDomainMod)

    fun isExist(id: Int): Boolean
}