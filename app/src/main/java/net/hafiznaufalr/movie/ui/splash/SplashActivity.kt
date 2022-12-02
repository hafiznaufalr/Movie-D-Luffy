package net.hafiznaufalr.movie.ui.splash

import android.animation.Animator
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import net.hafiznaufalr.movie.data.preferences.Preferences
import net.hafiznaufalr.movie.databinding.ActivitySplashBinding
import net.hafiznaufalr.movie.domain.base.ResultData
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.ui.main.MainActivity
import net.hafiznaufalr.movie.viewmodels.MovieViewModel

class SplashActivity : MovieBaseActivity<ActivitySplashBinding>() {
    private val viewModel: MovieViewModel by viewModels()
    private var getDataFinish = false
    private var animationFinish = false

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun init() {}

    override fun initData() {
        if (Preferences.getGenres() == null) {
            viewModel.getMovieGenres()
        } else {
            getDataFinish = true
            launchMainActivity()
        }
    }

    override fun initListener() {
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                animationFinish = true
                launchMainActivity()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }

    private fun launchMainActivity() {
        if (getDataFinish && animationFinish) {
            startActivity(
                Intent(
                    this@SplashActivity, MainActivity::class.java
                )
            )
            finish()
        }
    }

    override fun observer() {
        viewModel.movieGenres.observe(this) {
            when (it) {
                is ResultData.Loading -> {
                }

                is ResultData.Success -> {
                    Preferences.putGenres(it.data)

                    getDataFinish = true
                    launchMainActivity()
                }

                is ResultData.Failure -> {

                }
            }
        }
    }
}