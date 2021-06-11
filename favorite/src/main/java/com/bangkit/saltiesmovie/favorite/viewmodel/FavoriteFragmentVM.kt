package com.bangkit.saltiesmovie.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bangkit.saltiesmovie.core.domainlayer.usecase.UseCase

class FavoriteFragmentVM(val repo: UseCase): ViewModel() {
    val pagingData = repo.getFavoritePaging().cachedIn(viewModelScope)
}