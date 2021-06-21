package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var dummyMovies: ArrayList<Movie>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catalogueRepository: CatalogueRepository

    @Mock
    lateinit var observer: Observer<ArrayList<Movie>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getListMovie() {
        dummyMovies = DummyData.generateDummyMovies()

        val movies = MutableLiveData<ArrayList<Movie>>()
        movies.value = DummyData.generateDummyMovies()

        `when`(catalogueRepository.getListMovies()).thenReturn(movies)

        movieViewModel.getListMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

        val listMovie = movieViewModel.getListMovie().value as ArrayList<Movie>

        assertNotNull(listMovie)
        assertEquals(dummyMovies.size, listMovie.size)
    }
}