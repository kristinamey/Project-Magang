package com.example.sinow

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_feedback.*

class feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        btnkirim.setOnClickListener {
            //tuliskan post ke backend
        }
    }

    private fun kritikdansaran() {
        val namanya = nama.text.toString()
        val emailnya = email.text.toString()
        val komentarnya = komentar.text.toString()
        val loading: ProgressDialog
        loading = ProgressDialog.show(
            this,
            "Sedang memproses...",
            "Tunggu...",
            false,
            false
        )
        val stringRequest    = object : StringRequest(Request.Method.POST, "${resources.getString(R.string.base_url)}api/kritiksaran",
            Response.Listener { response ->
                if(response.contains("data")){
                    loading.dismiss()
                } else {
                    loading.dismiss()
                    Toast.makeText(this, "kritik dan saran tidak terekam", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                loading.dismiss()
                Toast.makeText(this, "Error server", Toast.LENGTH_SHORT).show()
            }
        ){
           override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["nama"] = namanya
                params["email"] = emailnya
                params["komentar"] = komentarnya
                return params
            }
        }
        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun validate() : Boolean{
        if(nama.text.toString().isEmpty()){
            nama.error = "harap isi bidang ini"
            return false
        }
        return true
    }
}