package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.CatalogueRepository
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.di.Injection
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailmovie.DetailMovieViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailtvshow.DetailTvShowViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie.viewmodel.MovieViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.tvshow.viewmodel.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogueRepository: CatalogueRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                        instance = this
                    }
                }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mCatalogueRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mCatalogueRepository) as T
            }

            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                return DetailTvShowViewModel(mCatalogueRepository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(mCatalogueRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}