package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*

class utamabelajarhuruf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamabelajarhuruf)
        
        keluar.setOnClickListener {
            val intent = Intent(this, belajar::class.java)
            startActivity(intent)
        }
    }
}