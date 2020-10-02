package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hiburan.*
import kotlinx.android.synthetic.main.activity_menyanyi.*
import kotlinx.android.synthetic.main.activity_quis.*

class menyanyi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menyanyi)

        keluar3.setOnClickListener {
            onBackPressed()
//            val intent = Intent(this, hiburan::class.java)
//            startActivity(intent)
        }
    }
}