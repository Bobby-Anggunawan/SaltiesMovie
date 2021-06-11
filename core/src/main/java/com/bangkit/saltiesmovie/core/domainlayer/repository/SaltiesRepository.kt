package com.bangkit.saltiesmovie.core.domainlayer.repository

import androidx.paging.PagingData
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import kotlinx.coroutines.flow.Flow

interface SaltiesRepository {
    fun getDiscoverPagingData(): Flow<PagingData<MoviePageDomainMod>>

    fun getFavoritePagingData(): Flow<PagingData<MoviePageDomainMod>>

    suspend fun getOnlineInfo(id: Int): Flow<MovieDetailDomainMod>

    fun addToDatabase(data: MovieDetailDomainMod)

    fun removeFromDatabase(data: MovieDetailDomainMod)

    fun isExist(id: Int): Boolean
}