package com.example.madmeditationapp

import Repository
import android.net.Uri
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.adapters.AdapterFromPhoto
import com.example.madmeditationapp.adapters.FeelingsAdapter
import com.example.madmeditationapp.data.DataStoreManager
import com.example.madmeditationapp.data.Models.LoginModel
import com.example.madmeditationapp.data.Network.MeditationApi
import com.example.madmeditationapp.databinding.FragmentProfileFileBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ProfileFileFragment(val user: LoginModel) : Fragment(R.layout.fragment_profile_file) {
    private lateinit var binding: FragmentProfileFileBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var photoPicker: ActivityResultLauncher<PickVisualMediaRequest>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonExitAccaunt = view.findViewById<TextView>(R.id.ExitButton)
        binding = FragmentProfileFileBinding.bind(view)
        /*val images = mutableListOf<Uri>()
        images.add(Uri.parse("android.resource://" + context?.packageName + "/" + R.drawable.rectangle_81))
        adapterFromPhoto = AdapterFromPhoto(images)
        binding.recyclerView.adapter = adapterFromPhoto*/

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



        val repository = Repository(requireContext())
        viewModel = MainViewModel(repository)
        photoPicker = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            it?.let {
                val bitmap = getBitmap(requireContext().contentResolver, it)
                if (bitmap != null) viewModel.addImage(bitmap)
            }
        }

        val galleryAdapter = AdapterFromPhoto(
            click = { uri -> openPhoto(uri) },
            addClick = { openPhotoPicker() }
        )
        viewModel.getImages()
        binding.recyclerView.adapter = galleryAdapter
        lifecycleScope.launch {
            viewModel.uris.collect {
                galleryAdapter.submitList(it)
            }
        }



        Picasso.get()
            .load(user.avatar)
            .into(binding.UserAva)
        binding.UserName.text = user.nickName
    }
    private fun openPhoto(uri: Uri) {
        val PFragment = PhotoFragment()

        val bundle = Bundle()
        bundle.putString("uri", uri.toString())
        PFragment.setArguments(bundle)
        parentFragmentManager.commit {
            replace(R.id.BaseFragment, PFragment)
        }
    }

    private fun openPhotoPicker() {
        photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}