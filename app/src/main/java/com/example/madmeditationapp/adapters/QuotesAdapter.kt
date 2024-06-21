package com.example.madmeditationapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madmeditationapp.R
import com.example.madmeditationapp.data.Models.QuoteListModel
import com.squareup.picasso.Picasso

class QuotesAdapter() : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    private var quotes : List<QuoteListModel> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun SetData(quotes: List<QuoteListModel>){
        this.quotes = quotes
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.requireViewById(R.id.FeelingImageVertical)
        val title : TextView = view.requireViewById(R.id.TitleTextVertical)
        val description : TextView = view.requireViewById(R.id.DescriptionTextVertical)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_vertical_list , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.title.text = quote.title
        holder.description.text = quote.description
        Picasso.get()
            .load(quote.image)
            .into(holder.image)
    }
}