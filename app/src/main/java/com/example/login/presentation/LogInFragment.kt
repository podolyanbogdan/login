package com.example.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.login.viewmodel.UserViewModel
import com.example.login.R
import com.example.login.databinding.FragmentLogInBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        binding.btnLogIn.setOnClickListener {
            val login = binding.edLogin.text.toString()
            val password = binding.edPass.text.toString()
            logInValidation(password = password, login = login)
        }
        return binding.root
    }

    private fun loginSuccessful(login: String) {
        Toast.makeText(context, R.string.login_successful, Toast.LENGTH_SHORT).show()
        view?.let { Navigation.findNavController(it).navigate(R.id.fromLogInToHello) }
        userViewModel.userNameModel.value = login
        binding.edPass.text.clear()
    }

    private fun message(content: Int) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    private fun logInValidation(password: String, login: String) {
        val allSymbols: Pattern = Pattern.compile("[^A-Za-z0-9]")
        val matcherByPass: Matcher = allSymbols.matcher(password)
        val matcherByLogin: Matcher = allSymbols.matcher(login)
        val passHasSpecialSymbols: Boolean = matcherByPass.find()
        val loginHasSpecialSymbols: Boolean = matcherByLogin.find()

        when{
            password.isEmpty() && login.isEmpty() -> message(R.string.two_lines_empty)
            password.isNotEmpty() && login.isEmpty() -> message(R.string.need_add_login)
            password.isEmpty() && login.isNotEmpty() -> message(R.string.need_add_pass)

            password.length > 10 -> message(R.string.pass_need_more_ten)
            password.length < 5 -> message(R.string.pass_need_not_more_ten)
            password.contains(' ') -> message(R.string.pass_no_probels)
            passHasSpecialSymbols -> message(R.string.pass_no_symbol)

            login.length > 10 -> message(R.string.login_need_not_more_ten)
            login.length < 5 -> message(R.string.login_need_more_four)
            login.contains(' ') -> message(R.string.login_no_probels)
            loginHasSpecialSymbols -> message(R.string.login_no_symbol)

            login == "admin" && password == "admin" -> loginSuccessful(login)
            else -> message(R.string.wrong_data)

        }
    }
}