package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailmovie

import androidx.lifecycle.ViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getDetailMovie(movieId: Int): Movie? = catalogueRepository.getDetailMovie(movieId)

}