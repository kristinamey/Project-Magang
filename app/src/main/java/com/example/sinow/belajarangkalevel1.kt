package com.example.sinow

import android.app.ProgressDialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.net.rtp.AudioStream
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelAngka1
import com.example.sinow.model.ModelHuruf
import com.example.sinow.model.ModelWarna
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_belajarangkalevel1.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class belajarangkalevel1 : AppCompatActivity() {
    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_belajarangkalevel1)
        getJSON()

        lanjut.setOnClickListener {
            getJSONnextPage()
        }
        kembali.setOnClickListener {
            getJSONprevPage()
        }

        keluar.setOnClickListener {
            onBackPressed()
        }
        reload.setOnClickListener {
            getJSON()
        }
    }

    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")

            prev_page_url = json.getString("prev_page_url")
            next_page = json.getString("next_page_url")
            if (!json.getString("next_page_url").isNullOrEmpty()){
                lanjut.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                kembali.isVisible =false
            } else {
                kembali.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelAngka1>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelAngka1>>(hasil, list)
            satu.text = data[0].tulisan

            /*val media = MediaPlayer()
            media.setDataSource(this, Uri.parse(data[0].sound))
            media.prepare()
            media.start()*/
            gambar_angkalv1.setOnClickListener {
                val media2 = MediaPlayer()
                try {
                    media2.setDataSource(this, Uri.parse(data[0].sound))
                    //media.setAudioStreamType(AudioManager.STREAM_MUSIC)
                    media2.prepare()
                    media2.start()
                } catch (e : IOException){
                    Log.e("ANGKA", e.toString())
                }
            }
            if(data.size != 0)(
                    gambar_angkalv1.loadSvgOrOthers(data[0].gambar)
                    )
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
                    this@belajarangkalevel1,
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/angka?tipe=satuan")
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
                lanjut.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                kembali.isVisible =false
            } else {
                kembali.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelAngka1>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelAngka1>>(hasil, list)
            if(data.size != 0){
                satu.text = data[0].tulisan
                gambar_angkalv1.loadSvgOrOthers(data[0].gambar)
                gambar_angkalv1.setOnClickListener {
                    val media2 = MediaPlayer()
                    try {
                        media2.setDataSource(this, Uri.parse(data[0].sound))
                        //media.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        media2.prepare()
                        media2.start()
                    } catch (e : IOException){
                        Log.e("ANGKA", e.toString())
                    }
                }
                val media = MediaPlayer.create(this, Uri.parse(data[0].sound))
                media.start()
                media.pause()
            }
            if(data.size != 0)(
                    gambar_angkalv1.loadSvgOrOthers(data[0].gambar)
                    )
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
                    this@belajarangkalevel1,
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
                lanjut.isVisible=true
            }
            if (json.getString("prev_page_url").isNullOrEmpty()){
                kembali.isVisible =false
            } else {
                kembali.isVisible =true
            }

            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelAngka1>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelAngka1>>(hasil, list)
            if(data.size != 0)(
                    gambar_angkalv1.loadSvgOrOthers(data[0].gambar)
                    )
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
                    this@belajarangkalevel1,
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