package com.example.sinow

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sinow.api.RequestHandler
import com.example.sinow.model.ModelHuruf
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_quis.*
import kotlinx.android.synthetic.main.activity_quis.keluar
import kotlinx.android.synthetic.main.activity_utamabelajarhuruf.*
import org.json.JSONException
import org.json.JSONObject

class utamabelajarhuruf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utamabelajarhuruf)

        viewmengenalhuruf1.offscreenPageLimit = 2
        viewmengenalhuruf1.adapter = PagerAdapter(supportFragmentManager)
        buttontab.setupWithViewPager(viewmengenalhuruf1)

        val viewGroup = buttontab.getChildAt(0) as ViewGroup
        val tabsCount = viewGroup.childCount
        buttontab.setSelectedTabIndicatorColor(0)
        for (j in 0 until tabsCount) {
            viewGroup.getChildAt(j).setOnTouchListener { _, _ -> true }
        }

        group_kiri.setOnClickListener {
            viewmengenalhuruf1.currentItem = 0
        }
        group_kanan.setOnClickListener {
            viewmengenalhuruf1.currentItem = 1
        }

        keluar.setOnClickListener {
            onBackPressed()
        }
    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        private val fragmentlist =
            listOf(Fragment_tab1mengenalhuruf(), Fragment_tab2mengenalhuruf())

        override fun getItem(position: Int): Fragment {
            return fragmentlist[position]
        }

        override fun getCount(): Int {
            return fragmentlist.size
        }
    }
}
