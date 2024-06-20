package com.example.madmeditationapp.data.Models

data class FeelingListModel(
    var id:Int,
    var title:String,
    var image: String,
    var position: Int
)

data class FeelingsModel(
    var success:Boolean,
    var data:List<FeelingListModel>
)