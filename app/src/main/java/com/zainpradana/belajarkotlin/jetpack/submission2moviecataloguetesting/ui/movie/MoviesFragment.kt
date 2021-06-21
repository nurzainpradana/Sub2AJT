package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.Movie
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.databinding.FragmentMoviesBinding
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailmovie.DetailMovieActivity
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie.adapter.CardViewMovieAdapter
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.movie.viewmodel.MovieViewModel
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.viewmodel.ViewModelFactory
import java.util.*

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance()
        val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        movieViewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
            binding.progressBar.visibility = View.GONE
            showRecyclerCardView(listMovie)
        })
    }

    private fun showRecyclerCardView(movies: ArrayList<Movie>) {
        with(binding) {
            rvMovies.setHasFixedSize(true)
            rvMovies.layoutManager = LinearLayoutManager(activity)

            val cardViewMovieAdapter = CardViewMovieAdapter(movies)
            rvMovies.adapter = cardViewMovieAdapter

            cardViewMovieAdapter.setOnItemClickCallback(object : CardViewMovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movie) {
                    showSelectedMovie(data)

                    val gotoDetailMovie = Intent(context, DetailMovieActivity::class.java)
                    gotoDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.movieId)

                    startActivity(gotoDetailMovie)
                }
            })
        }
    }

    private fun showSelectedMovie(movie: Movie) {
        Toast.makeText(context, "Kamu memilih " + movie.movieTitle, Toast.LENGTH_SHORT).show()
    }
}