package com.mahmoudroid.marvelcharacters.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudroid.marvelcharacters.CharactersActivity
import com.mahmoudroid.marvelcharacters.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splash_image.alpha = 0f
        splash_image.animate().setDuration(1000).alpha(1f).withEndAction {
            val i = Intent(this, CharactersActivity::class.java)
            startActivity(i)
            finish()
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }
    }
}