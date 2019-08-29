package com.example.android.pizzaapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.animation.DecelerateInterpolator

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorCompat

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    val STARTUP_DELAY: Long = 300
    val ANIM_ITEM_DURATION: Long = 1000
    val ITEM_DELAY: Long = 300
    private val animationStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        window.decorView.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        if (!hasFocus || animationStarted) {
            return
        }

        animate()

        super.onWindowFocusChanged(hasFocus)
    }

    private fun animate() {
        val logoImageView = findViewById<ImageView>(R.id.img_logo)
        val container = findViewById<ViewGroup>(R.id.container)
        val backgroundSplash = findViewById<ImageView>(R.id.img_background)

        ViewCompat.animate(logoImageView)
            .translationY(-290f)
            .setStartDelay(STARTUP_DELAY)
            .setDuration(ANIM_ITEM_DURATION)
            .setInterpolator(DecelerateInterpolator(1.2f))
            .start()

        ViewCompat.animate(backgroundSplash)
            .alpha(0.3F)

            .setStartDelay(STARTUP_DELAY)
            .setDuration(ANIM_ITEM_DURATION)
            .setInterpolator(DecelerateInterpolator(1.2f))
            .start()

        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            val viewAnimator: ViewPropertyAnimatorCompat

            viewAnimator = if (v !is Button) {
                ViewCompat.animate(v)
                    .translationY(50f)
                    .alpha(1f)
                    .setStartDelay(ITEM_DELAY * i + 500)
                    .setDuration(1000)
            } else {
                ViewCompat.animate(v)
                    .scaleY(1f)
                    .scaleX(1f)
                    .setStartDelay(ITEM_DELAY * i + 500)
                    .setDuration(500)
            }

            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }
    }
}
