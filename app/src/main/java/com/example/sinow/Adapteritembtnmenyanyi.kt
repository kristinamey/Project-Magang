package com.example.sinow

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.decode.SvgDecoder
import coil.request.LoadRequest
import com.example.sinow.model.ModelMenyanyi
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.itembtnmenyanyi.view.*
import kotlinx.android.synthetic.main.itemtab2mengenalhuruf.view.*
import java.util.*

class Adapteritembtnmenyanyi(private val onClick:(ModelMenyanyi) -> Unit)
    : RecyclerView.Adapter<Adapteritembtnmenyanyi.ViewHolder>() {

    var items : List<ModelMenyanyi> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapteritembtnmenyanyi.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.itembtnmenyanyi))
    }
    override fun onBindViewHolder(holder: Adapteritembtnmenyanyi.ViewHolder, position: Int) {
        val data = items[position]

        with(holder.itemView){
            nyanyi.loadSvgOrOthers(data.gambar)
            song.setOnClickListener {
                onClick(items[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
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