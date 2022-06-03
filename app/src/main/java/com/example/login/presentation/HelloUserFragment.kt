package com.example.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.login.databinding.FragmentHelloUserBinding
import com.example.login.viewmodel.UserViewModel

class HelloUserFragment : Fragment() {
    private lateinit var binding: FragmentHelloUserBinding
    private val userViewModel: UserViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelloUserBinding.inflate(inflater, container, false)
        userViewModel.userNameModel.observe(this, { name->
            binding.tvHelloUser.text = "Hello $name"
        })
        return binding.root
    }
}