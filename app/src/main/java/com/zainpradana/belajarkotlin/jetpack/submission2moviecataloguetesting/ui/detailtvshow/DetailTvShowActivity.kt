package com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.ui.detailtvshow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.R
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.data.source.local.entity.TvShow
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.databinding.ActivityDetailTvShowBinding
import com.zainpradana.belajarkotlin.jetpack.submission2moviecataloguetesting.viewmodel.ViewModelFactory
import jp.wasabeef.glide.transformations.BlurTransformation

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvShowId = intent.getIntExtra(EXTRA_TV_SHOW, 0)
        val factory = ViewModelFactory.getInstance(this)
        val detailTvShowViewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        detailTvShowViewModel.getTvShow(tvShowId).observe(this, { tvShow ->
            binding.progressBar.visibility = View.GONE
            setView(tvShow)
        })
    }

    private fun setView(tvShow: TvShow?) {
        if (tvShow != null) {
            with(binding) {
                tvDetailTvShowTitle.text = tvShow.tvShowTitle
                tvDetailTvShowDescription.text = tvShow.tvShowDescription
                tvDetailTvShowGenre.text = tvShow.tvShowGenre
                tvDetailTvShowYear.text = tvShow.tvShowYear

                Glide.with(applicationContext)
                        .load(tvShow.tvShowPoster)
                        .apply(RequestOptions().override(150, 150))
                        .into(imgDetailTvShowPoster)

                Glide.with(applicationContext)
                        .load(tvShow.tvShowPoster)
                        .apply(RequestOptions.bitmapTransform(BlurTransformation(25)))
                        .into(imgDetailTvShowPosterBlur)

                val actionBar = supportActionBar
                if (actionBar != null) {
                    actionBar.title = getString(R.string.tvshow) + " : " + tvShow.tvShowTitle
                }
            }
        }
    }

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}