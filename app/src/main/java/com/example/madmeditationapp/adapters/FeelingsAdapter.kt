package com.example.madmeditationapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madmeditationapp.R
import com.example.madmeditationapp.data.Models.FeelingListModel
import com.squareup.picasso.Picasso

class FeelingsAdapter() : RecyclerView.Adapter<FeelingsAdapter.ViewHolder>() {

    private var feelings: List<FeelingListModel> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(feelings: List<FeelingListModel>) {
        this.feelings = feelings
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val feelingImage : ImageView = view.findViewById(R.id.FeelingImage)
        val feelingTitle : TextView = view.findViewById(R.id.FeelingTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_horizontal_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feeling = feelings[position]
        holder.feelingTitle.text = feeling.title
        Picasso.get()
            .load(feeling.image)
            .into(holder.feelingImage)

    }

    override fun getItemCount(): Int {
        return feelings.size
    }

}