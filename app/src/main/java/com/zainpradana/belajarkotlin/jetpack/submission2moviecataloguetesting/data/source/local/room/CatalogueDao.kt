package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieEntity")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movieEntity WHERE movieSaved = 1")
    fun getSavedMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM movieEntity WHERE movieId = :movieId")
    fun getDetailMovie(movieId: String): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM tvShowEntity")
    fun getTvShows(): LiveData<List<TvShow>>

    @Query("SELECT * FROM tvShowEntity WHERE tvShowSaved = 1")
    fun getSavedTvShow(): LiveData<List<TvShow>>

    @Query("SELECT * FROM tvShowEntity WHERE tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId: String): LiveData<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShow)

    @Update
    fun updateTvShow(tvShow: TvShow)

}