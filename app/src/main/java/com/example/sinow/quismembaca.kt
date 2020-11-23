package com.example.sinow

import android.app.ProgressDialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHewan
import com.example.sinow.model.ModelQuis
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_hewan.*
import kotlinx.android.synthetic.main.activity_quismembaca.*
import kotlinx.android.synthetic.main.activity_quismembaca.keluar
import kotlinx.android.synthetic.main.activity_quismembaca.next
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class quismembaca : AppCompatActivity() {
    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""
    val adapteritemmembaca : Adapteritembtnquismembaca by lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnquismembaca(::tombol)
    }

    private fun tombol (data: ModelQuis){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quismembaca)
        getJSON()
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
            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelQuis>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelQuis>>(hasil, list)

            val media2 = MediaPlayer()
            media2.setDataSource(this, Uri.parse(data[0].pertanyaan))
            media2.prepare()
            media2.start()

            quis1.text = data[0].opsi_a
            quis2.text = data[0].opsi_b
            quis3.text = data[0].opsi_c
            quis4.text = data[0].opsi_d

            quis1.setOnClickListener {

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
                        val list = object : TypeToken<ArrayList<ModelQuis>>() {}.type
                        val data = Gson().fromJson<ArrayList<ModelQuis>>(hasil, list)
                        if(data.size != 0){
                            gambarhewan.loadSvgOrOthers(data[0].pertanyaan)
                            satu.text = "${data[0].tulisan_id} | ${data[0].tulisan_en}"
                        }
                        indo.setOnClickListener {
                            val media2 = MediaPlayer()
                            try {
                                media2.setDataSource(this, Uri.parse(data[0].sound_id))
                                //media.setAudioStreamType(AudioManager.STREAM_MUSIC)
                                media2.prepare()
                                media2.start()
                            } catch (e : IOException){
                                Log.e("HEWAN", e.toString())
                            }
                        }
                        inggris.setOnClickListener {
                            val media2 = MediaPlayer()
                            try {
                                media2.setDataSource(this, Uri.parse(data[0].sound_en))
                                //media.setAudioStreamType(AudioManager.STREAM_MUSIC)
                                media2.prepare()
                                media2.start()
                            } catch (e : IOException){
                                Log.e("HEWAN", e.toString())
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                if(data[0].opsi_a == data[0].jawaban){
                    Toast.makeText(this, "betul", Toast.LENGTH_SHORT).show()
                    //next
                }
                else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show()
                }
            }
            quis2.setOnClickListener {
                if(data[0].opsi_b == data[0].jawaban){
                    Toast.makeText(this, "betul", Toast.LENGTH_SHORT).show()
                    //tambahkan next
                }
                else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show()
                }
            }
            quis3.setOnClickListener {
                if(data[0].opsi_c == data[0].jawaban){
                    Toast.makeText(this, "betul", Toast.LENGTH_SHORT).show()
                    //tambahkan next
                }
                else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show()
                }
            }
            quis4.setOnClickListener {
                if(data[0].opsi_d == data[0].jawaban){
                    Toast.makeText(this, "betul", Toast.LENGTH_SHORT).show()
                    //tambahkan next
                }
                else {
                    Toast.makeText(this, "salah", Toast.LENGTH_SHORT).show()
                }
            }
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
                    this@quismembaca,
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/quiz?tipe=membaca")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }

}