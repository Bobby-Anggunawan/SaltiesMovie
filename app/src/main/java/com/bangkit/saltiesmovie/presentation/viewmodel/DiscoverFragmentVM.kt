package com.bangkit.saltiesmovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.bangkit.saltiesmovie.core.domainlayer.usecase.UseCase

class DiscoverFragmentVM(val repo: UseCase): ViewModel() {
    val pagingData = repo.getDiscoverPaging().cachedIn(viewModelScope)
}