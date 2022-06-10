package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentIntroduceYourSelfBinding
import com.example.login.interfaceLogin.LoginResultCallBacks
import com.example.login.model.UserModel
import com.example.login.viewmodel.LoginViewModelFactory
import com.example.login.viewmodel.UserLoginViewModel

class IntroduceYourSelfFragment : Fragment(), LoginResultCallBacks {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding : FragmentIntroduceYourSelfBinding =
           DataBindingUtil.inflate(inflater,R.layout.fragment_introduce_your_self, container, false)
        binding.viewModelLogin = ViewModelProvider(this, LoginViewModelFactory(this))[UserLoginViewModel::class.java]
        binding.viewModelLogin.isNavigate.observe(this,{ result ->
            if(result == 1){
                view?.let { Navigation.findNavController(it).navigate(R.id.fromIntroduceToQuestions) }
            }
        })
        return binding.root
    }

    override fun onResultMessage(message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}