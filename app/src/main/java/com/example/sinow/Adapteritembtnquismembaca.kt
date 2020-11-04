package com.example.sinow

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sinow.R
import com.example.sinow.model.ModelHuruf
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.itembtnquismembaca.view.*

class Adapteritembtnquismembaca(private val onClick:(ModelHuruf) -> Unit)
    : RecyclerView.Adapter<Adapteritembtnquismembaca.ViewHolder>() {

    var items : List<ModelHuruf> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.itembtnquismembaca))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        with(holder.itemView){
            quis4.text=data.huruf
            quis4.setOnClickListener {
                onClick(items[holder.adapterPosition])
            }
        }
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
    }
}