package com.example.madmeditationapp

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.data.DataStoreManager
import com.example.madmeditationapp.data.Models.LoginModel
import com.example.madmeditationapp.databinding.FragmentProfileFileBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProfileFileFragment(val user: LoginModel) : Fragment(R.layout.fragment_profile_file) {
    private lateinit var binding: FragmentProfileFileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonExitAccaunt = view.findViewById<TextView>(R.id.ExitButton)
        buttonExitAccaunt.setOnClickListener {
            lifecycleScope.launch {
                val loginModel = LoginModel(id = "", email = "", nickName = "", avatar = "", token = "")
                val dataStore = DataStoreManager(requireContext())
                dataStore.saveUserLogin(loginModel)
                parentFragmentManager.commit {
                    replace<LoginFragment>(R.id.BaseFragment)
                }
            }

        }

        binding = FragmentProfileFileBinding.bind(view)
        Picasso.get()
            .load(user.avatar)
            .into(binding.UserAva)
        binding.UserName.text = user.nickName
    }
}