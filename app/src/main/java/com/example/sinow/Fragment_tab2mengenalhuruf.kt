package com.example.sinow

import android.app.ProgressDialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHuruf
import com.example.sinow.model.ModelQuis
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_quismembaca.*
import kotlinx.android.synthetic.main.fragment_tab2mengenalhuruf.*
import org.json.JSONException
import org.json.JSONObject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Fragment_tab2mengenalhuruf : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""
    val adapteritem:Adaptermengenalhuruf by lazy(LazyThreadSafetyMode.NONE){
        Adaptermengenalhuruf(::tombol)
    }
    private fun tombol(s: ModelHuruf) {
        val media2 = MediaPlayer()
            media2.setDataSource(requireContext(), Uri.parse(s.sound))
            media2.prepare()
            media2.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab2mengenalhuruf, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getJSON()
        tab2mengenalhuruf.adapter = adapteritem
    }

    private fun showEmployee() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getString("data")
            val list = object : TypeToken<ArrayList<ModelHuruf>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHuruf>>(json, list)
            adapteritem.items = data

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
                    requireContext(),
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/huruf?list=all")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }
}