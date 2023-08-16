package mobi.mele.beers.ui.splash

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mobi.mele.beers.databinding.ActivitySplashBinding
import mobi.mele.beers.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySplashBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.beerAnimation.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {

                }

                override fun onAnimationEnd(animation: Animator) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
        }
}