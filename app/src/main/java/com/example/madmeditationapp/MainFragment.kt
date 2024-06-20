package com.example.madmeditationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madmeditationapp.data.Network.MeditationApIService
import com.example.madmeditationapp.data.Network.MeditationApi
import com.example.madmeditationapp.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var servise = MeditationApi.retrofitService
        /*val result = servise.getFeelings()*/
    }
}