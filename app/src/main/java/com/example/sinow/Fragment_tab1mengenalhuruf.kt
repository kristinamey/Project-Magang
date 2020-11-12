package com.example.sinow

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHuruf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_tab1mengenalhuruf.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Fragment_tab1mengenalhuruf : Fragment() {
    var JSON_STRING = ""
    private var param1: String? = null
    private var param2: String? = null
    var next_page = ""
    var prev_page_url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1mengenalhuruf, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_tab1mengenalhuruf().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getJSON()
        next.setOnClickListener {
            getJSONnextPage()
        }
        back.setOnClickListener {
            getJSONprevPage()
        }
    }

    private fun showEmployee() {
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
            val list = object : TypeToken<ArrayList<ModelHuruf>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHuruf>>(hasil, list)
            gambar_huruf.loadSvgOrOthers(data[0].gambar)
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/huruf")
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
            val list = object : TypeToken<ArrayList<ModelHuruf>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHuruf>>(hasil, list)
            gambar_huruf.loadSvgOrOthers(data[0].gambar)
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
            val list = object : TypeToken<ArrayList<ModelHuruf>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelHuruf>>(hasil, list)
            gambar_huruf.loadSvgOrOthers(data[0].gambar)
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