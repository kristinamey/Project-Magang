package com.example.sinow

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelBuah
import com.example.sinow.model.ModelHewan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_buahdansayur.*
import kotlinx.android.synthetic.main.activity_hewan.*
import kotlinx.android.synthetic.main.activity_quis.*
import kotlinx.android.synthetic.main.activity_quis.keluar
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class buahdansayur : AppCompatActivity() {
    var JSON_STRING = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buahdansayur)
        getJSON()

        keluar.setOnClickListener {
            onBackPressed()
        }
    }
    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")
            val next_page = jsonObject.getString("next_page_url")
            val prev_page_url = jsonObject.getString("prev_page_url")
            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelBuah>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelBuah>>(hasil, list)
            gambar_buah.loadSvgOrOthers(data[0].gambar)
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
                    this@buahdansayur,
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/membaca?tipe=buah")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }

    fun ImageView.loadSvgOrOthers(myUrl: String?) {
        myUrl?.let {
            if (it.toLowerCase(Locale.ENGLISH).endsWith("svg")) {
                val imageLoader = ImageLoader.Builder(this.context)
                    .componentRegistry {
                        add(SvgDecoder(this@loadSvgOrOthers.context))
                    }
                    .build()
                val request = LoadRequest.Builder(this.context)
                    .data(it)
                    .target(this)
                    .build()
                imageLoader.execute(request)
            } else {
                this.load(myUrl)
            }
        }
    }
}