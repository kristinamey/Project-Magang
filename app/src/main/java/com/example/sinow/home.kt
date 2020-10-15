package com.example.sinow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {

    lateinit var sharedpref: preferenceshelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedpref = preferenceshelper(this)
        callnama.text = sharedpref.getString(constant.PREF_USERNAME)

        logout.setOnClickListener {
            sharedpref.clear()
            notif("Keluar")
            pindahslur()
        }

        btn_belajar.setOnClickListener {
            val intent = Intent(this, belajar::class.java)
            (startActivity(intent))
        }

        btn_hiburan.setOnClickListener {
            val intent = Intent(this, hiburan::class.java)
            (startActivity(intent))
        }

        btn_quiz.setOnClickListener {
            val intent = Intent(this, quis::class.java)
            (startActivity(intent))
        }
        belajar_rem.setOnClickListener {
            val intent = Intent (this,belajar::class.java)
            (startActivity(intent))
        }
        hiburan_rem.setOnClickListener {
            val intent = Intent(this, hiburan::class.java)
            (startActivity(intent))
        }
        button_quiz.setOnClickListener {
            val intent = Intent(this, quis::class.java)
            (startActivity(intent))
        }

    }

    private fun pindahslur(){
        startActivity(Intent(this, welcomepgdua::class.java))
        finish()
    }

    private fun notif(pesan: String){
        Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
    }
}