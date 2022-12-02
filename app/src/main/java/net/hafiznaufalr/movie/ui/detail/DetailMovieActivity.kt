package net.hafiznaufalr.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.data.movie.model.MovieModel
import net.hafiznaufalr.movie.databinding.ActivityDetailMovieBinding
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.utils.ItemHorizontalMarginDecoration

class DetailMovieActivity : MovieBaseActivity<ActivityDetailMovieBinding>() {
    private val genreAdapter by lazy { GenreAdapter() }
    override val bindingInflater: (LayoutInflater) -> ActivityDetailMovieBinding
        get() = ActivityDetailMovieBinding::inflate

    override fun init() {
        // temporary best solution for transparent status bar without break navigation system styles
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT

        val decoration = ItemHorizontalMarginDecoration(
            this,
            horizontalMargin = R.dimen.size_4,
            firstLeftItemMargin = R.dimen.size_16,
            lastRightItemMargin = R.dimen.size_16
        )
        binding.recyclerViewNowGenre.apply {
            adapter = genreAdapter
            addItemDecoration(decoration)
        }
    }

    override fun initData() {
        intent.getParcelableExtra<MovieModel>(OBJ_MOVIE)?.let {
            binding.textViewTitle.text = it.title
            binding.textViewRating.text = String.format("%s/10", it.voteAverage)
            binding.textViewReleaseDate.text = it.releaseDate
            binding.textViewPopularity.text = it.popularity.toString()
            binding.textViewDescription.text = it.overview

            Glide.with(binding.imageViewBackdrop.context)
                .load(BuildConfig.BASE_IMAGE_URL + it.backdropPath)
                .into(binding.imageViewBackdrop)

            genreAdapter.differ.submitList(it.genreString)
        }
    }

    override fun initListener() {
        binding.imageViewBack.setOnClickListener {
            finish()
        }
    }

    override fun observer() {
    }

    companion object {
        const val OBJ_MOVIE = "OBJ_MOVIE"
        fun newInstance(context: Context, objMovie: MovieModel) {
            context.startActivity(
                Intent(
                    context,
                    DetailMovieActivity::class.java
                ).apply {
                    putExtra(OBJ_MOVIE, objMovie)
                }
            )
        }
    }
}