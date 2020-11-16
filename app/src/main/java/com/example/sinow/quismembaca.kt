package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sinow.model.ModelHuruf
import com.example.sinow.model.ModelQuis
import kotlinx.android.synthetic.main.activity_quis.*

class quismembaca : AppCompatActivity() {



    val adapteritemmembaca : Adapteritembtnquismembaca by lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnquismembaca(::tombol)
    }

    private fun tombol (Data: ModelQuis){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quismembaca)

        keluar.setOnClickListener {
        onBackPressed()
        }
    }

}