package com.example.sinow

import android.app.ProgressDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHuruf
import com.example.sinow.model.ModelMenyanyi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_menyanyi.*
import kotlinx.android.synthetic.main.fragment_tab2mengenalhuruf.*
import org.json.JSONException
import org.json.JSONObject

class menyanyi : AppCompatActivity() {
    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""

    val adapteritemmenyanyi : Adapteritembtnmenyanyi by lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnmenyanyi(::tombol)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menyanyi)
        getJSON()
        menyanyi.adapter= adapteritemmenyanyi

        keluar3.setOnClickListener {
            onBackPressed()
        }
    }
    private fun tombol(Data: ModelMenyanyi){
        val media2 = MediaPlayer()
        media2.setDataSource(this, Uri.parse(Data.sound))
        media2.prepare()
        media2.start()
    }
    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")
            val song = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelMenyanyi>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelMenyanyi>>(song, list)
            adapteritemmenyanyi.items = data

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun getJSON() {
        class GetJSON :
            AsyncTask<Void?, Void?, String>() {

            var loading: ProgressDialog? = null
            override fun onPreExecute() {
                super.onPreExecute()
                loading = ProgressDialog.show(
                    this@menyanyi,
                    "Sedang memproses...",
                    "Tunggu...",
                    false,
                    false
                )
            }

            override fun onPostExecute(s: String) {
                super.onPostExecute(s)
                loading!!.dismiss()
                JSON_STRING = s
                showEmployee()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/menyanyi")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }
}