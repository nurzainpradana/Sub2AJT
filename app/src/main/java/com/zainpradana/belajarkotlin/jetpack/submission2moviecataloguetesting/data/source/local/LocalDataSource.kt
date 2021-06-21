package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local

import android.os.Handler
import android.os.Looper
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.util.DummyData

class LocalDataSource private constructor(private val dummyData: DummyData) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(dummyData: DummyData): LocalDataSource =
                instance ?: synchronized(this) {
                    instance ?: LocalDataSource(dummyData).apply { instance = this }
                }
    }

    fun getListMovies(callback: LoadListMoviesCallback) {
         handler.postDelayed({ callback.onAllMoviesReceived(dummyData.generateDummyMovies())}, SERVICE_LATENCY_IN_MILLIS)
    }


    fun getListTvShows(callback: LoadListTvShowsCallback) {
        handler.postDelayed({ callback.onAllTvShowReceived(dummyData.generateDummyTvShow())}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadListTvShowsCallback {
        fun onAllTvShowReceived(listTvShowsCallback: List<TvShow>)
    }

    interface LoadListMoviesCallback {
        fun onAllMoviesReceived(listMovieResponses: List<Movie>)
    }
}

