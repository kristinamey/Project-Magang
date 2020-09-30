package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*

class quis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quis)

        btn_quisberhitung.setOnClickListener {
            val intent = Intent(this, quisberhitung::class.java)
            (startActivity(intent))
        }
        btn_quismembaca.setOnClickListener {
            val intent = Intent(this, quismembaca::class.java)
            (startActivity(intent))
        }
    }
}