package net.hafiznaufalr.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import net.hafiznaufalr.movie.BuildConfig
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.data.movie.model.MovieModel
import net.hafiznaufalr.movie.databinding.ActivityDetailMovieBinding
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.utils.ItemHorizontalMarginDecoration
import net.hafiznaufalr.movie.viewmodels.MovieViewModel

class DetailMovieActivity : MovieBaseActivity<ActivityDetailMovieBinding>() {
    private val viewModel: MovieViewModel by viewModels()
    private val genreAdapter by lazy { GenreAdapter() }
    private val reviewAdapter by lazy { ReviewAdapter() }
    private var reviewSkeleton: Skeleton? = null
    private var keyTrailer = ""

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

        binding.recyclerViewReview.apply {
            adapter = reviewAdapter
        }
        reviewSkeleton = binding.recyclerViewReview.applySkeleton(
            R.layout.item_review, 2
        )
    }

    override fun initData() {
        intent.getParcelableExtra<MovieModel>(OBJ_MOVIE)?.let {
            binding.textViewTitle.text = it.title
            binding.textViewRating.text = String.format("%s/10", it.voteAverage)
            binding.textViewReleaseDate.text = it.releaseDate
            binding.textViewPopularity.text = it.popularity.toString()
            binding.textViewDescription.text = it.overview

            val backdrop = it.backdropPath.ifEmpty { it.posterPath }
            Glide.with(binding.imageViewBackdrop.context)
                .load(BuildConfig.BASE_IMAGE_URL + backdrop)
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewBackdrop)

            genreAdapter.differ.submitList(it.genreString)

            viewModel.getMovieReviews(it.id)
            viewModel.getMovieTrailerKey(it.id)
        }
    }

    override fun initListener() {
        binding.imageViewBack.setOnClickListener {
            finish()
        }

        binding.iclNetworkError.textViewRefresh.setOnClickListener {
            initData()
            showNetworkError(false)
        }

        binding.imageViewTrailer.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${keyTrailer}")
                )
            )

        }
    }

    override fun observer() {
        viewModel.reviews.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    reviewSkeleton?.showSkeleton()
                }

                is ResultData.Success -> {
                    showNetworkError(false)

                    reviewSkeleton?.showOriginal()
                    binding.textViewNoReviews.isVisible = it.data.isEmpty()
                    reviewAdapter.differ.submitList(it.data)
                }

                is ResultData.Failure -> {
                    reviewSkeleton?.showOriginal()
                    showNetworkError(true)
                }
            }
        }

        viewModel.trailerKey.observe(this) {
            when (it) {
                is ResultData.Loading -> {}

                is ResultData.Success -> {
                    keyTrailer = it.data
                    binding.imageViewTrailer.isVisible = keyTrailer.isNotEmpty()
                }

                is ResultData.Failure -> {}
            }
        }
    }

    private fun showNetworkError(show: Boolean) {
        binding.iclNetworkError.root.isVisible = show
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