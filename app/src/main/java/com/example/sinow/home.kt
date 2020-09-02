package com.example.sinow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_belajar.setOnClickListener {
            val intent = Intent(this, belajar::class.java)
            (startActivity(intent))
        }

        btn_hiburan.setOnClickListener {
            val intent = Intent(this, hiburan::class.java)
            (startActivity(intent))
        }

        btn_quiz.setOnClickListener {
            val intent = Intent (this, quis::class.java)
            (startActivity(intent))
        }

    }
}