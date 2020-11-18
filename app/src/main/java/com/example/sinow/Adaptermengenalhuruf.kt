package com.example.sinow

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class Adaptermengenalhuruf(private val onClick:(String) -> Unit)
    : RecyclerView.Adapter<Adaptermengenalhuruf.ViewHolder>() {

    var items : List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.fragment_tab2mengenalhuruf))
    }

    override fun onBindViewHolder(holder: Adaptermengenalhuruf.ViewHolder, position: Int) {
        val data = items[position]
        with(holder.itemView){
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
    }


}