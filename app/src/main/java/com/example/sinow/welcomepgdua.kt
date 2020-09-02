package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_welcomepgdua.*

class welcomepgdua : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcomepgdua)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        btnSave.setOnClickListener {
            savedata()
        }

        btn_lanjut.setOnClickListener {
            val intent = Intent(this, home::class.java)
            (startActivity(intent))
        }
    }
        private fun savedata() {
            val nama = inputnama.text.toString()
            val umur = inputumur.text.toString()

            val user = Users(nama,umur)
            val userId = ref.push().key.toString()

            ref.child(userId).setValue(user).addOnCompleteListener {
                Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
                inputnama.setText("")
                inputumur.setText("")

            }
        }
    }
