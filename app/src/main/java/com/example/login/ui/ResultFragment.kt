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
import com.example.login.viewmodel.UserAuthorizationViewModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val userAuthorizationViewModel: UserAuthorizationViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        initUserInfo()
        binding.btnTryAgain.setOnClickListener {
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.resultToIntroduce) }
        }

        return binding.root
    }

    private fun initUserInfo(){
        userAuthorizationViewModel.userNameModel.observe(this, { name ->
            binding.tvResultName.text = name
        })
        userAuthorizationViewModel.userSurnameModel.observe(this, { surname ->
            binding.tvResultSurname.text = surname
        })
        userAuthorizationViewModel.userAgeModel.observe(this, { age ->
            binding.tvResultAge.text = age.toString()
        })
        userAuthorizationViewModel.correctAnswerModel.observe(this, { correct ->
            binding.tvResultCorrect.text = correct.toString()
        })
        userAuthorizationViewModel.unCorrectAnswerModel.observe(this, { uncorrect ->
            binding.tvResultUncorrect.text = uncorrect.toString()
        })

    }

}