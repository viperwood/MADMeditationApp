package com.example.madmeditationapp.data.Models

data class QuoteListModel(
    val id:Int,
    val title:String,
    val image:String,
    val description:String
)

data class QuoteModel(
    val success:Boolean,
    val data:List<QuoteListModel>
)