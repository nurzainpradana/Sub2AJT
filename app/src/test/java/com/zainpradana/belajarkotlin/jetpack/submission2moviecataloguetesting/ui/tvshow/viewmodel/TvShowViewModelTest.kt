package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.tvshow.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var dummyTvShows: ArrayList<TvShow>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catalogueRepository: CatalogueRepository

    @Mock
    lateinit var observer: Observer<ArrayList<TvShow>>

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getListTvShow() {
        dummyTvShows = DummyData.generateDummyTvShow()

        val mTvShow = MutableLiveData<ArrayList<TvShow>>()
        mTvShow.value = DummyData.generateDummyTvShow()

        `when`(catalogueRepository.getListTvShow()).thenReturn(mTvShow)

        tvShowViewModel.getListTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)

        val listTvShow = tvShowViewModel.getListTvShow().value as ArrayList<TvShow>

        assertNotNull(listTvShow)
        assertEquals(listTvShow.size, dummyTvShows.size)
    }
}