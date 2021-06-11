package com.bangkit.saltiesmovie.di

import android.content.Context
import androidx.room.Room
import com.bangkit.saltiesmovie.core.datalayer.datasource.MyDB
import com.bangkit.saltiesmovie.core.datalayer.repository.DiscoverPagingData
import com.bangkit.saltiesmovie.core.datalayer.datasource.RemoteDataSource
import com.bangkit.saltiesmovie.core.datalayer.repository.FavoritePagingSource
import com.bangkit.saltiesmovie.core.datalayer.repository.RepositoryImplementation
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import com.bangkit.saltiesmovie.core.domainlayer.usecase.SaltiesInteractor
import com.bangkit.saltiesmovie.core.domainlayer.usecase.UseCase
import com.bangkit.saltiesmovie.presentation.viewmodel.DetailFragmentVM
import com.bangkit.saltiesmovie.presentation.viewmodel.DiscoverFragmentVM
import com.google.gson.Gson
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object StorageModul {
    val storageModule = module {

        fun provideRepository(remoteDataSource: RemoteDataSource, ps: DiscoverPagingData, psFav: FavoritePagingSource, db: MyDB.MyDao): SaltiesRepository{
            return RepositoryImplementation(remoteDataSource, ps, psFav, db)
        }

        fun provideUseCase(repo: SaltiesRepository): UseCase{
            return SaltiesInteractor(repo)
        }

        fun provideDB(context: Context): MyDB.MyDao{
            val db = Room.databaseBuilder(
                context,
                MyDB.AppDatabase::class.java, "SaltiesDB"
            ).build()

            return db.saltiesDao()
        }

        single {
            RemoteDataSource()
        }

        single{
            Gson()
        }

        single{
            DiscoverPagingData(get())
        }

        single {
            FavoritePagingSource(get())
        }

        //provide SaltiesRepository di domain bukan RepositoryImplementation di data
        single {
            provideRepository(get(), get(), get(), get())
        }
        //provide UseCase di domain dengan object SaltiesInteractor
        single{
            provideUseCase(get())
        }

        single{
            provideDB(get())
        }

        viewModel {
            DetailFragmentVM(get())
        }
        viewModel {
            DiscoverFragmentVM(get())
        }
    }
}