package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sinow.mewarnai
import kotlinx.android.synthetic.main.activity_belajar.*
import kotlinx.android.synthetic.main.activity_hiburan.*
import kotlinx.android.synthetic.main.activity_home.*

class hiburan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiburan)

        btn_menyanyi.setOnClickListener {
            val intent = Intent(this, menyanyi::class.java)
            (startActivity(intent))
        }

        btn_mewarnai.setOnClickListener {
            val intent = Intent(this, mewarnai::class.java)
            (startActivity(intent))
        }

        button_menyanyi.setOnClickListener {
            val intent = Intent(this, menyanyi::class.java)
            (startActivity(intent))
        }
        button_mewarnai.setOnClickListener {
            val intent = Intent(this, mewarnai::class.java)
            (startActivity(intent))
        }
        keluar1.setOnClickListener {
onBackPressed()
//            val intent = Intent(this, home::class.java)
//            (startActivity(intent))
        }
    }
}