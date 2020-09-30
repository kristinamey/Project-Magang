package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_belajar.*
import kotlinx.android.synthetic.main.activity_quis.*
import kotlinx.android.synthetic.main.activity_utamamembaca.*
import kotlinx.android.synthetic.main.activity_utamamembaca.keluar

class utamamembaca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamamembaca)

        btn_hewan.setOnClickListener {
            val intent = Intent(this, hewan::class.java)
            (startActivity(intent))
        }
        btn_buah.setOnClickListener {
            val intent = Intent (this, buahdansayur::class.java)
            (startActivity(intent))
        }
        keluar.setOnClickListener {
            val intent = Intent(this, belajar::class.java)
            startActivity(intent)
        }
    }
}