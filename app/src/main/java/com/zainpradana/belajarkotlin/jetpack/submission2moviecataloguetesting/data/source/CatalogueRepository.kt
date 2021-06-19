package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source

import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.LocalDataSource
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow

/*
    Untuk menghubungkan kelas RemoteDataSource
 */
class CatalogueRepository private constructor(private val localDataSource: LocalDataSource): CatalogueDataSource {
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(localData: LocalDataSource): CatalogueRepository =
                instance ?: synchronized(this) {
                    instance ?: CatalogueRepository(localData).apply { instance = this }
                }
    }

    override fun getListMovies(): ArrayList<Movie> {
        val movies = localDataSource.getListMovies()
        val movieList = ArrayList<Movie>()
        for (mv in movies) {
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
        return movieList
    }

    override fun getListTvShow(): ArrayList<TvShow> {
        val tvShows = localDataSource.getListTvShows()
        val tvShowList = ArrayList<TvShow>()
        for (tv in tvShows) {
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
        return tvShowList
    }

    override fun getDetailTvShow(tvShowId: Int): TvShow? {
        val tvShows = localDataSource.getListTvShows()
        var tvShow: TvShow? = null

        for (tv in tvShows) {
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
        return tvShow
    }

    override fun getDetailMovie(movieId: Int): Movie? {
        val movies = localDataSource.getListMovies()
        var movie: Movie? = null

        for (mv in movies) {
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
        return movie
    }
}