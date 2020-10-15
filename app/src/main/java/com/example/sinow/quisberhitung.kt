package com.example.sinow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_quis.keluar
import kotlinx.android.synthetic.main.activity_quisberhitung.*

class quisberhitung : AppCompatActivity() {
    val adapteritem: Adapteritembtnquisberhitung by lazy(LazyThreadSafetyMode.NONE) {
        Adapteritembtnquisberhitung(::tombol)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quisberhitung)

        viewquisberhitung1.offscreenPageLimit = 2
        viewquisberhitung1.adapter = PagerAdapter(supportFragmentManager)
        buttontabberhitung.setupWithViewPager(viewquisberhitung1)

        val viewGroup = buttontabberhitung.getChildAt(0) as ViewGroup
        val tabsCount = viewGroup.childCount
        buttontabberhitung.setSelectedTabIndicatorColor(0)
        for (j in 0 until tabsCount) {
            viewGroup.getChildAt(j).setOnTouchListener { _, _ -> true }
        }

        group_min.setOnClickListener {
            viewquisberhitung1.currentItem = 0
        }
        group_plus.setOnClickListener {
            viewquisberhitung1.currentItem = 1
        }

        keluar.setOnClickListener {
            onBackPressed()
        }
    }

    private fun tombol(Data: String) {

    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        private val fragmentlist =
            listOf(Fragment_tab1quisberhitung(), Fragment_tab2quisberhitung())

        override fun getItem(position: Int): Fragment {
            return fragmentlist[position]
        }

        override fun getCount(): Int {
            return fragmentlist.size
        }
    }
}
