package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getListTvShow(): LiveData<ArrayList<TvShow>> = catalogueRepository.getListTvShow()
}