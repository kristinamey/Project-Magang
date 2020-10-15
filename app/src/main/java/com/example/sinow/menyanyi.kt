package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menyanyi.*

class menyanyi : AppCompatActivity() {

    val adapteritemmenyanyi : Adapteritembtnmenyanyi by Lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnmenyanyi(::tombol)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menyanyi)

        keluar3.setOnClickListener {
            onBackPressed()
        }
    }
    private fun tombol(Data: String){

    }
}