package com.example.madmeditationapp.data.Network

import com.example.madmeditationapp.data.Models.FeelingsModel
import com.example.madmeditationapp.data.Models.LoginModel
import com.example.madmeditationapp.data.Models.QuoteModel
import com.example.madmeditationapp.data.Models.UserLoginModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface MeditationApIService {
    @POST("user/login")
    suspend fun login(@Body login: UserLoginModel): Response<LoginModel>

    @GET("feelings")
    suspend fun getFeelings(): FeelingsModel

    @GET("quotes")
    suspend fun getQuotes(): QuoteModel
}