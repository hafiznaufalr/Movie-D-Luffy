package net.hafiznaufalr.movie.ui.main

import android.view.LayoutInflater
import androidx.activity.viewModels
import net.hafiznaufalr.movie.databinding.ActivityMainBinding
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.viewmodels.MovieViewModel

class MainActivity : MovieBaseActivity<ActivityMainBinding>() {
    private val viewModel: MovieViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun init() {
    }

    override fun initData() {
        viewModel.getNowPlaying()
    }

    override fun initListener() {
    }

    override fun observer() {
        viewModel.nowPlaying.observe(this) {
            when (it) {
                is ResultData.Loading -> {

                }

                is ResultData.Success -> {
                    binding.textView.text = it.data.data[0].overview
                }

                is ResultData.Failure -> {

                }
            }
        }
    }

}