package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_utamabelajarangka.*

class utamabelajarangka : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamabelajarangka)

        btn_level1.setOnClickListener {
            val intent = Intent(this, belajarangkalevel1::class.java)
            (startActivity(intent))
        }
        }
    }