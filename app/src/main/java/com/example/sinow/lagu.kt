package com.example.sinow

import android.app.ProgressDialog
import android.media.MediaPlayer
import android.net.Uri
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
import com.example.sinow.model.ModelMenyanyi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_belajarangkalevel1.*
import kotlinx.android.synthetic.main.itembtnmenyanyi.*
import kotlinx.android.synthetic.main.lagu.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class lagu : AppCompatActivity() {
    var JSON_STRING = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lagu)
        getJSON()
        intent.getStringExtra("id")
    }
    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")
            val song = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelMenyanyi>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelMenyanyi>>(song, list)
            judullagu.text = data[0].judul
            play.setOnClickListener {
                val media2 = MediaPlayer()
                try {
                    media2.setDataSource(this, Uri.parse(data[0].sound))
                    media2.prepare()
                    media2.start()
                } catch (e : IOException){
                    Log.e("Lagu", e.toString())
                }
            }
            if(data.size != 0)(
                    konten_huruf.loadSvgOrOthers(data[0].gambar)
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
                    this@lagu,
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