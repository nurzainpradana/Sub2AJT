package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getListMovie() : LiveData<ArrayList<Movie>> = catalogueRepository.getListMovies()
}