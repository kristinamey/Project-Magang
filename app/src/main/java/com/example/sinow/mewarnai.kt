package com.example.sinow

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menyanyi.keluar3
import kotlinx.android.synthetic.main.activity_mewarnai.*

class mewarnai : AppCompatActivity() {

    val adapteritemmewarnai : Adapteritembtnmewarnai by lazy (LazyThreadSafetyMode.NONE){
        Adapteritembtnmewarnai(::tombol)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mewarnai)

        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)
        
        keluar3.setOnClickListener {
            onBackPressed()
        }
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
        imageView.setImageBitmap(bitmap)
        canvas_drawing.paintColor(resources.getColor(R.color.biru))
        canvas_drawing.paintStork(20)
    }
    private fun tombol(Data: String){

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_drawing -> canvas_drawing.paintColor(resources.getColor(R.color.colorAccent))
            R.id.ic_eraser -> canvas_drawing.paintColor(resources.getColor(R.color.biru))
            R.id.ic_restart -> canvas_drawing.clear()
            else -> {
            }
        }

        return true
    }
}