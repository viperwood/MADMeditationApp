package com.example.madmeditationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class Onboarding : Fragment(R.layout.fragment_onboarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.LoginButton)
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace<LoginFragment>(R.id.BaseFragment)
            }
        }
    }
}