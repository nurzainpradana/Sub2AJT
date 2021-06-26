package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.FakeCatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.LocalDataSource
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val local = mock(LocalDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(local)

    private val moviesResponse = DummyData.generateDummyMovies()
    private val movieId = moviesResponse[0].movieId

    private val tvShowsResponse = DummyData.generateDummyTvShow()
    private val tvShowId = tvShowsResponse[0].tvShowId



    @Test
    fun getListMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadListMoviesCallback)
                .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(local).getListMovies(any())

        val listMovie = LiveDataTestUtil.getValue(catalogueRepository.getListMovies())
        verify(local).getListMovies(any())

        assertNotNull(listMovie)
        assertEquals(listMovie.size.toLong(), moviesResponse.size.toLong())
    }

    @Test
    fun getListTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadListTvShowsCallback)
                .onAllTvShowReceived(tvShowsResponse)
            null
        }.`when`(local).getListTvShows(any())

        val listTvShow = LiveDataTestUtil.getValue(catalogueRepository.getListTvShow())
        verify(local).getListTvShows(any())

        assertNotNull(listTvShow)
        assertEquals(listTvShow.size.toLong(), tvShowsResponse.size.toLong())

    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadListTvShowsCallback)
                .onAllTvShowReceived(tvShowsResponse)
            null
        }.`when`(local).getListTvShows(any())

        val detailTvShows = LiveDataTestUtil.getValue(catalogueRepository.getListTvShow())
        verify(local).getListTvShows(any())

        assertNotNull(detailTvShows)
        assertEquals(detailTvShows[0].tvShowId, tvShowId)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LocalDataSource.LoadListMoviesCallback)
                .onAllMoviesReceived(moviesResponse)
            null
        }.`when`(local).getListMovies(any())

        val detailMovies = LiveDataTestUtil.getValue(catalogueRepository.getListMovies())
        verify(local).getListMovies(any())

        assertNotNull(detailMovies)
        assertEquals(detailMovies[0].movieId, movieId)
    }
}