package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentResultBinding
import com.example.login.viewmodel.UserLoginViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val userLoginViewModel: UserLoginViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.btnTryAgain.setOnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.resultToIntroduce) }
        }

        return binding.root
    }


}