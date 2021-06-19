package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailtvshow

import androidx.lifecycle.ViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShow(tvShowId: Int) : TvShow? = catalogueRepository.getDetailTvShow(tvShowId)
}