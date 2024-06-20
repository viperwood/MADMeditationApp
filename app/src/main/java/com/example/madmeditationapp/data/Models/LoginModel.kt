package com.example.madmeditationapp.data.Models

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserLoginModel(
    var email:String,
    var password:String
)
data class LoginModel(
    var id:String,
    var email: String,
    var nickName:String,
    var avatar:String,
    var token:String
)