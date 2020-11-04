package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_utamabelajarangka.*

class utamabelajarhuruf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamabelajarhuruf)

        btn_level1.setOnClickListener {
            val intent = Intent(this, belajarhuruflevel1::class.java)
            (startActivity(intent))
        }
        btn_level2.setOnClickListener {
            val intent = Intent(this, belajarhuruflevel2::class.java)
            (startActivity(intent))
        }
        btn_level3.setOnClickListener {
            val intent = Intent(this, belajarhuruflevel3::class.java)
            (startActivity(intent))
        }
        keluar.setOnClickListener {
            onBackPressed()
        }
    }
}