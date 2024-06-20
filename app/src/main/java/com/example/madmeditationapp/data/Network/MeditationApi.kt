package com.example.madmeditationapp.data.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MeditationApi {
    private const val BaseURL = "http://mskko2021.mad.hakta.pro/api/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BaseURL)
        .build()
    val retrofitService: MeditationApIService by lazy {
        retrofit.create(MeditationApIService ::class.java)
    }
}