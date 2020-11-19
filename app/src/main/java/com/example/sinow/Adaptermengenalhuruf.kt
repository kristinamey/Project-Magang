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
import com.example.sinow.model.ModelHuruf
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.itemtab2mengenalhuruf.view.*
import java.util.*

class Adaptermengenalhuruf(private val onClick:(ModelHuruf) -> Unit)
    : RecyclerView.Adapter<Adaptermengenalhuruf.ViewHolder>() {

    var items : List<ModelHuruf> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.itemtab2mengenalhuruf))
    }

    override fun onBindViewHolder(holder: Adaptermengenalhuruf.ViewHolder, position: Int) {
        val data = items[position]

        holder.itemView.view.isVisible = selectedItem == position

        with(holder.itemView){
            kotakmengenalhuruf.loadSvgOrOthers(data.gambar)
            kotakmengenalhuruf.setOnClickListener {
                onClick(items[holder.adapterPosition])
                selectedItem = holder.adapterPosition
                notifyDataSetChanged()
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