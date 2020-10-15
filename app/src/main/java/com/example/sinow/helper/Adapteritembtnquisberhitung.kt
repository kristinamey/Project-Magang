package com.example.sinow

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sinow.R
import kotlinx.android.extensions.LayoutContainer

class Adapteritembtnquisberhitung(private val onClick:(String) -> Unit)
    : RecyclerView.Adapter<Adapteritembtnquisberhitung.ViewHolder>() {

    var items : List<String> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.itembtnquisberhitung))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        with(holder.itemView){
        }
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
    }
}