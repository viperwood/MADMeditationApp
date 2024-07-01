package com.example.madmeditationapp

import Repository
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.madmeditationapp.data.DataStoreManager
import com.example.madmeditationapp.databinding.FragmentPhotoBinding
import kotlinx.coroutines.launch

class PhotoFragment : Fragment(R.layout.fragment_photo) {
    private lateinit var binding: FragmentPhotoBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var repository: Repository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoBinding.bind(view)
        repository = Repository(requireContext())
        viewModel = MainViewModel(repository)

        val uri: Uri?;
        val bundle = arguments
        if (bundle != null)
        {
            uri = Uri.parse(bundle.getString("uri"))
        }
        else
        {
            uri = null;
        }

        if (uri != null) {
            binding.Image.setImageURI(uri)

            binding.buttonDelete.setOnClickListener {
                deleteImage(uri)
            }

            binding.buttonClose.setOnClickListener {
                val dataStore = DataStoreManager(requireContext())
                dataStore.getUserInfo()
                lifecycleScope.launch {
                    dataStore.getUserInfo().collect{ user ->
                        if(user.email.isNotEmpty()){
                            parentFragmentManager.commit {
                                val profileFileFragment = ProfileFileFragment(user)
                                replace(R.id.BaseFragment, profileFileFragment)
                            }
                        }
                    }

                }
            }
        }
    }

    private fun deleteImage(uri: Uri) {
        val isDeleted = repository.deleteImage(uri)
        if (isDeleted) {
            viewModel.removeImage(uri)
            val dataStore = DataStoreManager(requireContext())
            dataStore.getUserInfo()
            lifecycleScope.launch {
                dataStore.getUserInfo().collect{ user ->
                    if(user.email.isNotEmpty()){
                        parentFragmentManager.commit {
                            val profileFileFragment = ProfileFileFragment(user)
                            replace(R.id.BaseFragment, profileFileFragment)
                        }
                    }
                }

            }
        } else {
            Toast.makeText(requireContext(), "Ошибка во время удаления изображения", Toast.LENGTH_SHORT).show()
        }
    }
}