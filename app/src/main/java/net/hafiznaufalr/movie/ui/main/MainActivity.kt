package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
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
    private var totalPage = 0
    private var currentPage = 1
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
        currentPage = 1
        viewModel.getNowPlaying()
        viewModel.getPopular(currentPage)
    }

    override fun initListener() {
        binding.iclNowPlaying.iclNetworkError.textViewRefresh.setOnClickListener {
            viewModel.getNowPlaying()
            showNowPlayingError(false)
        }

        binding.iclPopular.iclNetworkError.textViewRefresh.setOnClickListener {
            currentPage = 1
            viewModel.getPopular(currentPage)
            showPopularError(false)
        }

        binding.swipeRefresh.setOnRefreshListener {
            initData()
            binding.swipeRefresh.isRefreshing = false
        }

        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (totalPage >= currentPage) {
                    showLoadMore(true)
                    viewModel.getPopular(page = currentPage)
                } else {
                    showLoadMore(false)
                }
            }
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
                    if (currentPage == 1) {
                        popularSkeleton?.showSkeleton()
                    }
                }

                is ResultData.Success -> {
                    showPopularError(false)

                    popularSkeleton?.showOriginal()

                    if (currentPage == 1) {
                        binding.iclPopular.textViewNoData.isVisible = it.data.data.isEmpty()
                        popularAdapter.submitList(it.data.data, true)
                    } else {
                        popularAdapter.submitList(it.data.data)
                    }

                    currentPage = it.data.page + 1
                    if (totalPage == 0) {
                        totalPage = if (it.data.totalPages >= 10) 10 else it.data.totalPages
                    }
                }

                is ResultData.Failure -> {
                    popularSkeleton?.showOriginal()
                    popularAdapter.submitList(emptyList(), true)
                    showPopularError(true)
                }
            }
        }
    }

    private fun showLoadMore(show: Boolean) {

        binding.loadMore.rootView.isVisible = show
    }

}