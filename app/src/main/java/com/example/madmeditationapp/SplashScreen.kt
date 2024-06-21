package com.example.madmeditationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.data.DataStoreManager
import com.example.madmeditationapp.data.Models.LoginModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class SplashScreen : Fragment(R.layout.fragment_blank) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataStore = DataStoreManager(requireContext())
        dataStore.getUserInfo()
        lifecycleScope.launch {
            dataStore.getUserInfo().collect{ user ->
                if(user.email.isNotEmpty()){
                    parentFragmentManager.commit {
                        val mainFragment = MainFragment(user)
                        replace(R.id.BaseFragment, mainFragment)
                    }
                }
            }

        }

        val button = view.findViewById<ImageView>(R.id.Test)
        button.setOnClickListener {
            parentFragmentManager.commit {
                replace<Onboarding>(R.id.BaseFragment)
            }
        }
    }
}