package com.example.madmeditationapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.madmeditationapp.data.Models.LoginModel
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("data_store")
class DataStoreManager(val context : Context) {
    private val emailKey = stringPreferencesKey("AutorizationInfoEmail")
    private val idKey = stringPreferencesKey("AutorizationInfoId")
    private val avatarlKey = stringPreferencesKey("AutorizationInfoAvatar")
    private val tokenKey = stringPreferencesKey("AutorizationInfoToken")
    private val nickNameKey = stringPreferencesKey("AutorizationInfoNickName")
    suspend fun saveUserLogin(saveUser : LoginModel){
        context.datastore.edit {
            pref -> pref[emailKey] = saveUser.email
            pref[idKey] = saveUser.id
            pref[avatarlKey] = saveUser.avatar
            pref[tokenKey] = saveUser.token
            pref[nickNameKey] = saveUser.nickName
        }
    }

    fun getUserInfo() = context.datastore.data.map { pref ->
        return@map LoginModel(
            pref[emailKey] ?: "",
            pref[idKey] ?: "",
            pref[avatarlKey] ?: "",
            pref[tokenKey] ?: "",
            pref[nickNameKey] ?: ""
        )

    }
}