package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source

import androidx.lifecycle.LiveData
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

interface CatalogueDataSource {

    fun getListMovies(): LiveData<ArrayList<Movie>>

    fun getListTvShow(): LiveData<ArrayList<TvShow>>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShow?>

    fun getDetailMovie(movieId: Int): LiveData<Movie?>
}