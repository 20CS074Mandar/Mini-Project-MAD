package com.example.miniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        imageView=findViewById(R.id.lottieAnimationView)
        Thread{
            try {
                Thread.sleep(2500)
            }
            catch (e:InterruptedException)
            {
                e.printStackTrace()
            }
            startActivity(Intent(this,LoginActivity::class.java))
        }.start()
    }
}