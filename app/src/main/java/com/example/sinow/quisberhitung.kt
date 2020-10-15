package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quis.*

class quisberhitung : AppCompatActivity() {
    val adapteritem : Adapteritembtnquisberhitung by lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnquisberhitung(::tombol)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quisberhitung)

        keluar.setOnClickListener {
        onBackPressed()
        }
    }
    private fun tombol(Data : String){

    }
}

