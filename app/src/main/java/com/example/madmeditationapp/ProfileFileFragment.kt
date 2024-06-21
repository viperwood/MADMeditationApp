package com.example.madmeditationapp

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madmeditationapp.data.Models.LoginModel
import com.example.madmeditationapp.databinding.FragmentProfileFileBinding
import com.squareup.picasso.Picasso

class ProfileFileFragment(val user: LoginModel) : Fragment(R.layout.fragment_profile_file) {
    private lateinit var binding: FragmentProfileFileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileFileBinding.bind(view)
        Picasso.get()
            .load(user.avatar)
            .into(binding.UserAva)
        binding.UserName.text = user.nickName
    }
}