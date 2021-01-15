package com.example.uasmp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uasmp.R
import com.example.uasmp.models.Wisata
import kotlinx.android.synthetic.main.item_wisata.view.*

class WisataAdapter(private var data : List<Wisata>, private var context : Context) : RecyclerView.Adapter<WisataAdapter.MyHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_wisata, parent, false))
    }

    override fun onBindViewHolder(holder: WisataAdapter.MyHolder, position: Int) = holder.bind(data[position],context)

    override fun getItemCount() = data.size

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tourism: Wisata, context: Context){
            itemView.tvName.text = tourism.name
            itemView.tvLocation.text = tourism.location
            itemView.tvDescription.text = tourism.description
        }
    }
}

