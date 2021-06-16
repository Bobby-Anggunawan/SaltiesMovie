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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import java.util.concurrent.TimeUnit

object StorageModul {
    val storageModule = module {

        fun provideRepository(remoteDataSource: RemoteDataSource, ps: DiscoverPagingData, psFav: FavoritePagingSource, db: MyDB.MyDao): SaltiesRepository{
            return RepositoryImplementation(remoteDataSource, ps, psFav, db)
        }

        fun provideUseCase(repo: SaltiesRepository): UseCase{
            return SaltiesInteractor(repo)
        }

        single {
            RemoteDataSource(get())
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

        viewModel {
            DetailFragmentVM(get())
        }
        viewModel {
            DiscoverFragmentVM(get())
        }
    }

    val networkModule = module{
        single{
            val hostname = "api.themoviedb.org"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
                .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
                .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
                .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
                .build()

            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()
        }
    }

    val DBModule = module{
        fun provideDB(context: Context): MyDB.MyDao{
            val passphrase: ByteArray = SQLiteDatabase.getBytes("BangkitJagawana".toCharArray())
            val factory = SupportFactory(passphrase)

            val db = Room.databaseBuilder(
                context,
                MyDB.AppDatabase::class.java, "Salties.db"
            )
                .fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()

            return db.saltiesDao()
        }

        single{
            provideDB(get())
        }


    }
}