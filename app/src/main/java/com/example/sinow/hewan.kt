package com.example.sinow

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelAngka3
import com.example.sinow.model.ModelHewan
import com.example.sinow.model.ModelHuruf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_belajarangkalevel3.*
import kotlinx.android.synthetic.main.activity_hewan.*
import kotlinx.android.synthetic.main.activity_quis.*
import kotlinx.android.synthetic.main.activity_quis.keluar
import kotlinx.android.synthetic.main.fragment_tab1mengenalhuruf.*
import kotlinx.android.synthetic.main.fragment_tab1mengenalhuruf.gambar_huruf
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlinx.android.synthetic.main.activity_hewan.back as back1
import kotlinx.android.synthetic.main.fragment_tab1mengenalhuruf.next as next1

class hewan : AppCompatActivity() {
    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hewan)
        getJSON()
        next.setOnClickListener {
            getJSONnextPage()
        }
        back.setOnClickListener {
            getJSONprevPage()
        }

        keluar.setOnClickListener {
        onBackPressed()
        }
    }

    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")

            prev_page_url = json.getString("prev_page_url")
            next_page = json.getString("next_page_url")
            if (!json.getString("next_page_url").isNullOrEmpty()){
                next.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                back.isVisible =false
            } else {
                back.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelHewan>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHewan>>(hasil, list)
            gambarhewan.loadSvgOrOthers(data[0].gambar)
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
                    this@hewan,
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/membaca?tipe=hewan")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }

    private fun showNextPage() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")

            prev_page_url=json.getString("prev_page_url")
            next_page=json.getString("next_page_url")
            if (!json.getString("next_page_url").isNullOrEmpty()){
                next.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                back.isVisible =false
            } else {
                back.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelHewan>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHewan>>(hasil, list)
            gambarhewan.loadSvgOrOthers(data[0].gambar)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun getJSONnextPage() {
        class GetJSON :
            AsyncTask<Void?, Void?, String>() {

            var loading: ProgressDialog? = null
            override fun onPreExecute() {
                super.onPreExecute()
                loading = ProgressDialog.show(
                    this@hewan,
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
                showNextPage()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(next_page)
            }
        }

        val gj = GetJSON()
        gj.execute()
    }

    private fun showPrevPage() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")

            prev_page_url=json.getString("prev_page_url")
            next_page=json.getString("next_page_url")
            if (!json.getString("next_page_url").isNullOrEmpty()){
                next.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                back.isVisible =false
            } else {
                back.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelHewan>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHewan>>(hasil, list)
            gambarhewan.loadSvgOrOthers(data[0].gambar)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun getJSONprevPage() {
        class GetJSON :
            AsyncTask<Void?, Void?, String>() {

            var loading: ProgressDialog? = null
            override fun onPreExecute() {
                super.onPreExecute()
                loading = ProgressDialog.show(
                    this@hewan,
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
                showPrevPage()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequest(prev_page_url)
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