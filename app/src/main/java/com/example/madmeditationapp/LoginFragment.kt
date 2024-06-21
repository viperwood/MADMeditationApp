package com.example.madmeditationapp

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.data.Models.UserLoginModel
import com.example.madmeditationapp.data.Network.MeditationApi
import com.example.madmeditationapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.SignInButton)
        val service = MeditationApi.retrofitService
        binding = FragmentLoginBinding.bind(view)
        button.setOnClickListener {
            if (binding.Email.text.toString() != null && binding.Email.text.toString() != "") {
                binding.ErrorEmail.text = ""
                if (binding.Password.text.toString() != null && binding.Password.text.toString() != "") {
                    binding.ErrorPassword.text = ""
                    if ("@" in binding.Email.text.toString()) {
                        binding.ErrorEmail.text = ""
                        lifecycleScope.launch {
                            val userLog: UserLoginModel = UserLoginModel(
                                binding.Email.text.toString(),
                                binding.Password.text.toString()
                            )

                            val result =
                                service.login(UserLoginModel(userLog.email, userLog.password))
                            if (result.isSuccessful) {
                                parentFragmentManager.commit {
                                    val userlogin = checkNotNull(result.body())
                                    val mainFragment = MainFragment(userlogin)
                                    replace(R.id.BaseFragment, mainFragment)
                                }
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    result.message().toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    else{
                        binding.ErrorEmail.text = "Это не E-mail!"
                    }
                }
                else {
                    binding.ErrorPassword.text = "Пустая строка Password!"
                }
            }
            else{
                binding.ErrorEmail.text = "Пустая строка E-mail!"
            }
        }
    }
}