package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueDataSource
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

class FakeCatalogueRepository(private val localDataSource: LocalDataSource):
    CatalogueDataSource {
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(localData: LocalDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(localData).apply { instance = this }
            }
    }

    override fun getListMovies(): LiveData<ArrayList<Movie>> {
        val listMovie = MutableLiveData<ArrayList<Movie>>()
        localDataSource.getListMovies(object: LocalDataSource.LoadListMoviesCallback {
            override fun onAllMoviesReceived(listMovieResponses: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for (mv in listMovieResponses) {
                    val movie = Movie(
                        movieId = mv.movieId,
                        movieTitle = mv.movieTitle,
                        movieDescription = mv.movieDescription,
                        movieGenre = mv.movieGenre,
                        moviePoster = mv.moviePoster,
                        movieYear = mv.movieYear
                    )
                    movieList.add(movie)
                }
                listMovie.postValue(movieList)
            }
        })
        return listMovie
    }

    override fun getListTvShow(): LiveData<ArrayList<TvShow>> {
        val listTvShow = MutableLiveData<ArrayList<TvShow>>()
        localDataSource.getListTvShows(object: LocalDataSource.LoadListTvShowsCallback{
            override fun onAllTvShowReceived(listTvShowsCallback: List<TvShow>) {
                val tvShowList = ArrayList<TvShow>()
                for (tv in listTvShowsCallback) {
                    val movie = TvShow(
                        tvShowId = tv.tvShowId,
                        tvShowDescription = tv.tvShowDescription,
                        tvShowGenre = tv.tvShowGenre,
                        tvShowPoster = tv.tvShowPoster,
                        tvShowTitle = tv.tvShowTitle,
                        tvShowYear = tv.tvShowYear
                    )
                    tvShowList.add(movie)
                }
                listTvShow.postValue(tvShowList)
            }
        })
        return listTvShow
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvShow?> {
        val detailTvShow = MutableLiveData<TvShow?>()
        localDataSource.getListTvShows(object: LocalDataSource.LoadListTvShowsCallback{
            override fun onAllTvShowReceived(listTvShowsCallback: List<TvShow>) {
                var tvShow: TvShow? = null

                for (tv in listTvShowsCallback) {
                    if (tv.tvShowId == tvShowId) {
                        tvShow = TvShow(
                            tvShowId = tv.tvShowId,
                            tvShowYear = tv.tvShowYear,
                            tvShowTitle = tv.tvShowTitle,
                            tvShowPoster = tv.tvShowPoster,
                            tvShowGenre = tv.tvShowGenre,
                            tvShowDescription = tv.tvShowDescription
                        )
                    }
                }
                detailTvShow.postValue(tvShow)
            }
        })
        return detailTvShow
    }

    override fun getDetailMovie(movieId: Int): LiveData<Movie?> {
        val detailMovie = MutableLiveData<Movie?>()

        localDataSource.getListMovies(object: LocalDataSource.LoadListMoviesCallback{
            override fun onAllMoviesReceived(listMovieResponses: List<Movie>) {
                var movie: Movie? = null

                for (mv in listMovieResponses) {
                    if (mv.movieId == movieId) {
                        movie = Movie(
                            movieId = mv.movieId,
                            movieTitle = mv.movieTitle,
                            movieDescription = mv.movieDescription,
                            movieGenre = mv.movieGenre,
                            moviePoster = mv.moviePoster,
                            movieYear = mv.movieYear
                        )
                    }
                }
                detailMovie.postValue(movie)
            }
        })
        return detailMovie
    }
}