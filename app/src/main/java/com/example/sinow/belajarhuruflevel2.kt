package com.example.sinow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*

class belajarhuruflevel2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_belajarhuruflevel2)

        keluar.setOnClickListener {
            onBackPressed()
//            val intent = Intent(this, utamabelajarangka::class.java)
//            startActivity(intent)
        }
    }
}