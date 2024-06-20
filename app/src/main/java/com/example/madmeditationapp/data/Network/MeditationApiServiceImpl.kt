package com.example.madmeditationapp.data.Network

import com.example.madmeditationapp.data.Models.UserLoginModel

class MeditationApiServiceImpl(private val meditationApIService: MeditationApIService)
{
    suspend fun login(userLoginModel: UserLoginModel) = meditationApIService.login(userLoginModel)
    suspend fun getFeelings() = meditationApIService.getFeelings()
    suspend fun getQuotes() = meditationApIService.getQuotes()
}