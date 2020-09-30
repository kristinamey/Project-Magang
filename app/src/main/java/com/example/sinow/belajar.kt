package com.example.sinow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_belajar.*

class belajar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_belajar)

        btn_mengenal_angka.setOnClickListener {
            val intent = Intent(this, utamabelajarangka::class.java)
            (startActivity(intent))
        }

        btn_mengenal_huruf.setOnClickListener {
            val intent = Intent (this, utamabelajarhuruf::class.java)
            (startActivity(intent))
        }

        btn_membaca.setOnClickListener {
            val intent = Intent(this, utamamembaca::class.java)
            (startActivity(intent))
        }

        btn_mengenal_warna.setOnClickListener {
            val intent = Intent(this, mengenalwarna::class.java)
            (startActivity(intent))
        }

        keluar2.setOnClickListener {
            val intent = Intent(this, home::class.java)
            startActivity(intent)
        }
        }
    }
