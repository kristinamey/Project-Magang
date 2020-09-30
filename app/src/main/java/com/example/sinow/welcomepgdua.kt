package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import com.example.sinow.helper.constant
import com.example.sinow.helper.preferenceshelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcomepgdua.*

class welcomepgdua : AppCompatActivity() {

    lateinit var sharedpref: preferenceshelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomepgdua)

        sharedpref = preferenceshelper(this)

        btnSave.setOnClickListener {
           if (inputnama.text!!.isNotEmpty() && inputumur.text!!.isNotEmpty()){ //masih bingung harusnya inputnama.text.isnotempery
               savesession(inputnama.text.toString(), inputumur.text.toString())
                notif("Berhasil Masuk")
               pindahslur()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (sharedpref.getBoolean(constant.PREF_IS_LOGIN)){
            pindahslur()
        }
    }
    private fun pindahslur(){
        startActivity(Intent(this, home::class.java))
        finish()
    }
    private fun savesession(username: String, umur: String){
        sharedpref.put(constant.PREF_USERNAME, username)
        sharedpref.put(constant.PREF_UMUR, umur)
        sharedpref.put(constant.PREF_IS_LOGIN, true)
    }

    private fun notif(pesan: String){
        Toast.makeText(applicationContext, pesan, Toast.LENGTH_SHORT).show()
    }

}
