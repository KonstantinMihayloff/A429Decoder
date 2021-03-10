package com.example.a429decoder.labelData

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.a429decoder.R

class LabelDataAdapter(private val labelDatas: List<LabelData>)
    : RecyclerView.Adapter<LabelDataAdapter.ViewHolder>(){

    // View Holder Definition
    // A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        // Getting CardView from layout file
        //var cardView = itemView.findViewById<CardView>(R.id.cardview)
        var cardView = itemView.findViewById<CardView>(R.id.cardview)

        // Getting TextViews from layout file
        var dataNameView = cardView.findViewById<TextView>(R.id.data_name)
        var dataContentView = cardView.findViewById<TextView>(R.id.data_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating layout file on ViewHolder creation
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_data, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting particular labelData
        val labelData = labelDatas[position]

        // Filling ViewHolder with info
        holder.cardView.tag = position
        holder.dataNameView.text = labelData.contentDescription
        holder.dataContentView.text = labelData.value
    }

    override fun getItemCount(): Int {
        // Returning size
        return labelDatas.size
    }
}