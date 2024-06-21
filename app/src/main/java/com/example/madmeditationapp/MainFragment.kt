package com.example.madmeditationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.adapters.FeelingsAdapter
import com.example.madmeditationapp.adapters.QuotesAdapter
import com.example.madmeditationapp.data.Models.LoginModel
import com.example.madmeditationapp.data.Network.MeditationApi
import com.example.madmeditationapp.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainFragment(val user: LoginModel) : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var feelingsAdapter: FeelingsAdapter
    private lateinit var quotesAdapter: QuotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = MeditationApi.retrofitService

        val profileButton = view.findViewById<ImageView>(R.id.ProfileButton)
        profileButton.setOnClickListener{
            parentFragmentManager.commit {
                val profileFileFragment = ProfileFileFragment(user)
                replace(R.id.BaseFragment, profileFileFragment)
            }
        }

        binding = FragmentMainBinding.bind(view)
        feelingsAdapter = FeelingsAdapter()
        quotesAdapter = QuotesAdapter()

        Picasso.get()
            .load(user.avatar)
            .into(binding.imageUser)

        binding.recyclerViewFeelings.adapter = feelingsAdapter
        binding.recyclerViewFeelingsVertical.adapter = quotesAdapter

        binding.textUserName.text = "С возвращением, ${user.nickName.toString()}!"
        lifecycleScope.launch {
            val resultquotes = service.getQuotes()
            quotesAdapter.SetData(resultquotes.data)

            val resultfeelings = service.getFeelings()
            feelingsAdapter.setData(resultfeelings.data)
        }
    }
}