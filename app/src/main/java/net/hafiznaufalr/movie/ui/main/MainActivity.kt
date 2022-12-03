package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import net.hafiznaufalr.movie.R
import net.hafiznaufalr.movie.databinding.ActivityMainBinding
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.ui.detail.DetailMovieActivity
import net.hafiznaufalr.movie.utils.ItemHorizontalMarginDecoration
import net.hafiznaufalr.movie.viewmodels.MovieViewModel

class MainActivity : MovieBaseActivity<ActivityMainBinding>() {
    private val viewModel: MovieViewModel by viewModels()
    private val nowPlayingAdapter by lazy {
        NowPlayingAdapter {
            DetailMovieActivity.newInstance(this, it)
        }
    }
    private val popularAdapter by lazy {
        PopularAdapter {
            DetailMovieActivity.newInstance(this, it)
        }
    }
    private var nowPlayingSkeleton: Skeleton? = null
    private var popularSkeleton: Skeleton? = null

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun init() {
        val decoration = ItemHorizontalMarginDecoration(
            this,
            horizontalMargin = R.dimen.size_4,
            firstLeftItemMargin = R.dimen.size_16,
            lastRightItemMargin = R.dimen.size_16
        )
        binding.iclNowPlaying.recyclerViewNowPlaying.apply {
            adapter = nowPlayingAdapter
            addItemDecoration(decoration)
        }

        nowPlayingSkeleton = binding.iclNowPlaying.recyclerViewNowPlaying.applySkeleton(
            R.layout.item_now_playing, 5
        )

        binding.iclPopular.recyclerViewPopular.apply {
            adapter = popularAdapter
        }

        popularSkeleton = binding.iclPopular.recyclerViewPopular.applySkeleton(
            R.layout.item_popular, 8
        )
    }

    override fun initData() {
        viewModel.getNowPlaying()
        viewModel.getPopular()
    }

    override fun initListener() {
        binding.iclNowPlaying.iclNetworkError.textViewRefresh.setOnClickListener {
            viewModel.getNowPlaying()
            showNowPlayingError(false)
        }

        binding.iclPopular.iclNetworkError.textViewRefresh.setOnClickListener {
            viewModel.getPopular()
            showPopularError(false)
        }

        binding.swipeRefresh.setOnRefreshListener {
            initData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun showPopularError(show: Boolean) {
        binding.iclPopular.iclNetworkError.root.isVisible = show
    }

    private fun showNowPlayingError(show: Boolean) {
        binding.iclNowPlaying.iclNetworkError.root.isVisible = show
    }

    override fun observer() {
        viewModel.nowPlaying.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    nowPlayingSkeleton?.showSkeleton()
                }

                is ResultData.Success -> {
                    showNowPlayingError(false)

                    nowPlayingSkeleton?.showOriginal()
                    binding.iclNowPlaying.textViewNoData.isVisible = it.data.data.isEmpty()
                    nowPlayingAdapter.differ.submitList(it.data.data)
                }

                is ResultData.Failure -> {
                    nowPlayingSkeleton?.showOriginal()
                    nowPlayingAdapter.differ.submitList(emptyList())
                    showNowPlayingError(true)
                }
            }
        }

        viewModel.popular.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                    popularSkeleton?.showSkeleton()
                }

                is ResultData.Success -> {
                    showPopularError(false)

                    popularSkeleton?.showOriginal()
                    binding.iclPopular.textViewNoData.isVisible = it.data.data.isEmpty()
                    popularAdapter.differ.submitList(it.data.data)
                }

                is ResultData.Failure -> {
                    popularSkeleton?.showOriginal()
                    popularAdapter.differ.submitList(emptyList())
                    showPopularError(true)
                }
            }
        }
    }

}