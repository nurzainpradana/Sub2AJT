package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.LocalDataSource
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    var detailMovieViewModel: DetailMovieViewModel? = null
    var movie: Movie? = null

    lateinit var dummyMovie: ArrayList<Movie>


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catalogueRepository: CatalogueRepository

    @Before
    fun setUp() {
        val movie = LocalDataSource.getInstance(DummyData)
        catalogueRepository = CatalogueRepository.getInstance(movie)
        detailMovieViewModel = DetailMovieViewModel(catalogueRepository)
    }

    @Test
    fun getDetailMovie() {
        dummyMovie = DummyData.generateDummyMovies()
//        val mDummyMovie = dummyMovie[0] as Movie
        lateinit var mDummyMovie: Movie

        `when`<Movie>(catalogueRepository.getDetailMovie(1)).thenReturn(DummyData.generateDummyMovies()[0])

        val detailMovie = detailMovieViewModel?.getDetailMovie(1)
        verify<CatalogueRepository>(catalogueRepository).getDetailMovie(1)

        Assert.assertNotNull(detailMovie)
        Assert.assertEquals(detailMovie!!.movieId.toLong(), movie!!.movieId.toLong())
        Assert.assertEquals(detailMovie.movieTitle, movie!!.movieTitle)
        Assert.assertEquals(detailMovie.moviePoster.toLong(), movie!!.moviePoster.toLong())
        Assert.assertEquals(detailMovie.movieGenre, movie!!.movieGenre)
        Assert.assertEquals(detailMovie.movieDescription, movie!!.movieDescription)
        Assert.assertEquals(detailMovie.movieYear, movie!!.movieYear)
    }
}