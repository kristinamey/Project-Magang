package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*

class quismembaca : AppCompatActivity() {

    val adapteritemmembaca : Adapteritembtnquismembaca by Lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnquismembaca(::tombol)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quismembaca)

        keluar.setOnClickListener {
        onBackPressed()
        }
    }
    private fun tombol(Data: String){

    }
}