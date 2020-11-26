package com.example.sinow

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHuruf
import com.example.sinow.model.ModelQuis
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_tab1quisberhitung.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Fragment_tab1quisberhitung : Fragment() {
    var JSON_STRING = ""
    var next_page = ""
    var prev_page_url = ""
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    private fun tombol (data: ModelQuis){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1quisberhitung, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_tab1quisberhitung().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getJSON()
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
            viewsoalberhitungplus.loadSvgOrOthers(data[0].pertanyaan)

            btn_jawaban1.text = data[0].opsi_a
            btn_jawaban2.text = data[0].opsi_b
            btn_jawaban3.text = data[0].opsi_c
            btn_jawaban4.text = data[0].opsi_d

            btn_jawaban1.setOnClickListener {
                if(data[0].opsi_a == data[0].jawaban){
                    Toast.makeText(requireContext(), "Benar", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban2.setOnClickListener {
                if(data[0].opsi_b == data[0].jawaban){
                    Toast.makeText(requireContext(), "Benar", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban3.setOnClickListener {
                if(data[0].opsi_c == data[0].jawaban){
                    Toast.makeText(requireContext(), "Benar", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban4.setOnClickListener {
                if(data[0].opsi_d == data[0].jawaban){
                    Toast.makeText(requireContext(), "Benar", Toast.LENGTH_SHORT).show()
             getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
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
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/quiz/?tipe=pengurangan")
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

    private fun showEmployee2() {
        try {
            val jsonObject = JSONObject(JSON_STRING)
            val json = jsonObject.getJSONObject("data")

            prev_page_url = json.getString("prev_page_url")
            next_page = json.getString("next_page_url")
            val hasil = json.getString("data")
            val list = object : TypeToken<ArrayList<ModelQuis>>() {}.type
            val data = Gson().fromJson<ArrayList<ModelQuis>>(hasil, list)

            viewsoalberhitungplus.loadSvgOrOthers(data[0].pertanyaan)

            btn_jawaban1.text = data[0].opsi_a
            btn_jawaban2.text = data[0].opsi_b
            btn_jawaban3.text = data[0].opsi_c
            btn_jawaban4.text = data[0].opsi_d

            btn_jawaban1.setOnClickListener {
                if(data[0].opsi_a == data[0].jawaban){
                    Toast.makeText(requireContext(), "Benar", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban2.setOnClickListener {
                if(data[0].opsi_b == data[0].jawaban){
                    Toast.makeText(requireContext(), "betul", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban3.setOnClickListener {
                if(data[0].opsi_c == data[0].jawaban){
                    Toast.makeText(requireContext(), "betul", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
            btn_jawaban4.setOnClickListener {
                if(data[0].opsi_d == data[0].jawaban){
                    Toast.makeText(requireContext(), "betul", Toast.LENGTH_SHORT).show()
                    getNext2()
                }
                else {
                    Toast.makeText(requireContext(), "salah", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun getNext2() {
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
                showEmployee2()
            }

            override fun doInBackground(vararg params: Void?): String {
                val rh = RequestHandler()
                return rh.sendGetRequest("${resources.getString(R.string.base_url)}api/quiz/?tipe=pengurangan")
            }
        }

        val gj = GetJSON()
        gj.execute()
    }
}