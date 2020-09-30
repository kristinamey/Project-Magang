package com.example.sinow

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.content.Intent

class SplashActivity : Activity() {
    
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, welcomepgdua::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}