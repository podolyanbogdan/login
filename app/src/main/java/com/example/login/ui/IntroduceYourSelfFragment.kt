package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.login.databinding.FragmentIntroduceYourSelfBinding
import com.example.login.model.UserModel
import com.example.login.viewmodel.UserAuthorizationViewModel

class IntroduceYourSelfFragment : Fragment() {
    private lateinit var binding: FragmentIntroduceYourSelfBinding
    private val userAutoViewModel: UserAuthorizationViewModel by activityViewModels()
    private val user = UserModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroduceYourSelfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            initUser()
            userAutoViewModel.confirmUserInfo(user, view)
            showError()
        }
    }

    private fun initUser() {
        val name = binding.edName.text.toString()
        val surname = binding.edSurname.text.toString()
        val age = binding.edAge.text.toString().toIntOrNull() ?: 0
        user.name = name
        user.surname = surname
        user.age = age
    }
    private fun showError(){
        userAutoViewModel.message.observe(this, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}