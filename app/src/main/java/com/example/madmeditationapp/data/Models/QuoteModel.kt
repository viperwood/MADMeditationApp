package com.example.madmeditationapp.data.Models

data class QuoteListModel(
    var id:Int,
    var title:String,
    var image:String,
    var description:String
)

data class QuoteModel(
    var success:Boolean,
    var data:List<QuoteListModel>
)