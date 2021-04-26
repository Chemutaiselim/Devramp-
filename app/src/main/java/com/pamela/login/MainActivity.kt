package com.pamela.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity: AppCompatActivity() {

    private var timeout: Long = 300
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler().postDelayed({
            val i = Intent(this, LogIn::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, timeout)


    }
}


