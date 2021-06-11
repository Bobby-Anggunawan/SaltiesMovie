package com.bangkit.saltiesmovie.core.domainlayer.usecase

import androidx.paging.PagingData
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import kotlinx.coroutines.flow.Flow

class SaltiesInteractor(val repo: SaltiesRepository): UseCase {
    override fun getDiscoverPaging(): Flow<PagingData<MoviePageDomainMod>> = repo.getDiscoverPagingData()

    override fun getFavoritePaging(): Flow<PagingData<MoviePageDomainMod>> = repo.getFavoritePagingData()

    override suspend fun getInfo(id: Int): Flow<MovieDetailDomainMod> = repo.getOnlineInfo(id)

    override fun addToDB(data: MovieDetailDomainMod) = repo.addToDatabase(data)

    override fun removeFromDB(data: MovieDetailDomainMod) = repo.removeFromDatabase(data)

    override fun isExist(id: Int): Boolean = repo.isExist(id)
}