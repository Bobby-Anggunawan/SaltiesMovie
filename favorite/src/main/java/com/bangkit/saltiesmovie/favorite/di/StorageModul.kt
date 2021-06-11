package com.bangkit.saltiesmovie.favorite.di

import com.bangkit.saltiesmovie.favorite.viewmodel.FavoriteFragmentVM
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object StorageModul {
    val favoriteModul = module {
        viewModel { FavoriteFragmentVM(get()) }
    }
}