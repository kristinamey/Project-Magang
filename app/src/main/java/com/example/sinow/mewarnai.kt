package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menyanyi.*

class mewarnai : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mewarnai)

        keluar3.setOnClickListener {
            val intent = Intent(this, hiburan::class.java)
            startActivity(intent)
        }
    }
}