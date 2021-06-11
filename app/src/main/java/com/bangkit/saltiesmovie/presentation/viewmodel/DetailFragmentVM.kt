package com.bangkit.saltiesmovie.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.saltiesmovie.core.datalayer.model.MovieDetailDataMod
import com.bangkit.saltiesmovie.core.domainlayer.model.MovieDetailDomainMod
import com.bangkit.saltiesmovie.core.domainlayer.repository.SaltiesRepository
import com.bangkit.saltiesmovie.core.domainlayer.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class DetailFragmentVM(val repo: UseCase): ViewModel() {
    suspend fun getData(id: Int) = repo.getInfo(id)
    private var favorit: MutableLiveData<Boolean>? = null

    fun initFavorit(track: MovieDetailDomainMod) {
        if(repo.isExist(track.id)){
            favorit!!.postValue(true)
        }
        else{
            favorit!!.postValue(false)
        }
    }

    fun getFavorit(track: MovieDetailDomainMod): LiveData<Boolean> {
        if (favorit == null) {
            favorit = MutableLiveData()
            initFavorit(track)
        }
        return favorit as MutableLiveData<Boolean>
    }

    fun postFavorit(track: MovieDetailDomainMod) {
        if (!repo.isExist(track.id)) {
            repo.addToDB(track)
            favorit?.postValue(true)
        } else {
            repo.removeFromDB(track)
            favorit?.postValue(false)
        }
    }

}