package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.R
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.databinding.ActivityDetailMovieBinding
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.viewmodel.ViewModelFactory
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailMovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        val factory = ViewModelFactory.getInstance(this)
        val detailMovieViewModel = ViewModelProvider(this, factory) [DetailMovieViewModel::class.java]

        detailMovieViewModel.getDetailMovie(movieId).observe(this, { movie ->
            binding.progressBar.visibility = View.GONE
            setView(movie)
        })
    }

    private fun setView(movie: Movie?) {
        if (movie != null) {
            with(binding) {
                tvDetailMovieTitle.text = movie.movieTitle
                tvDetailMovieDescription.text = movie.movieDescription
                tvDetailMovieGenre.text = movie.movieGenre
                tvDetailMovieYear.text = movie.movieYear

                Glide.with(applicationContext)
                        .load(movie.moviePoster)
                        .apply(RequestOptions().override(150, 150))
                        .into(imgDetailMoviePoster)

                Glide.with(applicationContext)
                        .load(movie.moviePoster)
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(25)))
                        .into(imgDetailMoviePosterBlur)

                val actionBar = supportActionBar
                if (actionBar != null) actionBar.title = getString(R.string.movie) + " : " + movie.movieTitle
            }
        }
    }

    companion object {
        @JvmField
        var EXTRA_MOVIE = "extra_movie"
    }
}