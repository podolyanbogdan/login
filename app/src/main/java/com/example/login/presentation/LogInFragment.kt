package com.example.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.login.databinding.FragmentLogInBinding
import com.example.login.viewmodel.UserModel
import com.example.login.viewmodel.UserViewModel


class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val userViewModel: UserViewModel by activityViewModels()
    private val user = UserModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            val login = binding.edLogin.text.toString()
            val password = binding.edPass.text.toString()
            user.login = login
            user.password = password
            userViewModel.message.observe(this, Observer {
                it.getContentIfNotHandled()?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
            userViewModel.successfulLogin(userModel = user, view = view)
        }
    }

}