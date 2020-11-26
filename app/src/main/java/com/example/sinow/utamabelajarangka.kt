package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*
import kotlinx.android.synthetic.main.activity_utamabelajarangka.*
import kotlinx.android.synthetic.main.activity_utamabelajarangka.keluar

class utamabelajarangka : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamabelajarangka)

        btn_level1.setOnClickListener {
            val intent = Intent(this, belajarangkalevel1::class.java)
            (startActivity(intent))
        }
        btn_level2.setOnClickListener {
            val intent = Intent(this, belajarangkalevel2::class.java)
            (startActivity(intent))
        }
        btn_level3.setOnClickListener {
            val intent = Intent(this, belajarangkalevel3::class.java)
            (startActivity(intent))
        }

        satuan.setOnClickListener {
            val intent = Intent(this, belajarangkalevel1::class.java)
            (startActivity(intent))
        }
        puluhan.setOnClickListener {
            val intent = Intent(this, belajarangkalevel2::class.java)
            (startActivity(intent))
        }
        ratusan.setOnClickListener {
            val intent = Intent(this, belajarangkalevel3::class.java)
            (startActivity(intent))
        }
        keluar.setOnClickListener {
         onBackPressed()
        }
    }
}