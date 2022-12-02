package net.hafiznaufalr.movie.ui.splash

import android.animation.Animator
import android.content.Intent
import android.view.LayoutInflater
import net.hafiznaufalr.movie.databinding.ActivitySplashBinding
import net.hafiznaufalr.movie.ui.base.MovieBaseActivity
import net.hafiznaufalr.movie.ui.main.MainActivity

class SplashActivity : MovieBaseActivity<ActivitySplashBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun init() {}

    override fun initData() {
    }

    override fun initListener() {
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                startActivity(
                    Intent(
                        this@SplashActivity, MainActivity::class.java
                    )
                )
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }

        })
    }

    override fun observer() {
    }
}