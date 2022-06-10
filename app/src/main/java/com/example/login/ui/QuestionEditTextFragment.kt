package com.example.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentQuestionEditTextBinding
import com.example.login.questions.Questions
import com.example.login.questions.QuestionEditTextModel
import com.example.login.viewmodel.UserLoginViewModel

class QuestionEditTextFragment : Fragment() {

    private lateinit var binding: FragmentQuestionEditTextBinding
    private val userLoginViewModel: UserLoginViewModel by activityViewModels()

    private var currentPosition = 1
    private lateinit var questionsList: ArrayList<QuestionEditTextModel>
    private var selectedOptionPosition = 0
    private var isClicked = false
    private var correctAnswer = 0
    private var unCorrectAnswer = 0

    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionEditTextBinding.inflate(inflater, container, false)
        questionsList = Questions.getQuestionsEditText()
        setQuestion()
        buttonClick()
        return binding.root
    }


    private fun setQuestion() {
        val question = questionsList[currentPosition - 1]

        if (currentPosition == questionsList.size) {
            binding.btnSubmitEdit.text = getString(R.string.finish_two)
        } else {
            binding.btnSubmitEdit.text = getString(R.string.submit_btn)
        }


        binding.progressBar.progress = currentPosition
        binding.tvProgress.text =
            "$currentPosition" + getString(R.string.chertochka) + binding.progressBar.max
        binding.tvQuestName.text = question.questEditList["questionName"] as CharSequence?

        isClicked = false
    }

    private fun answerView(answer: Int, drawableView: Int, message: String) {
        when (answer) {
            1 -> {
                binding.tvUserResultOfAnswer.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
                binding.tvUserResultOfAnswer.text = message
            }
        }
    }

    private fun buttonClick() {
        binding.btnSubmitEdit.setOnClickListener {
            if (binding.edUserAnswer.text.isNotEmpty()) {
                isClicked = true
            }
            if (!isClicked) {
                Toast.makeText(context, R.string.need_to_choice, Toast.LENGTH_SHORT).show()
            } else if(isClicked) {
                binding.edUserAnswer.isEnabled = true
                binding.tvUserResultOfAnswer.visibility = View.GONE
                if (currentPosition == questionsList.size) {
                    binding.btnSubmitEdit.text = getString(R.string.next_module)
                } else {
                    binding.btnSubmitEdit.text = getString(R.string.go_to_next_question)
                }
                if (binding.edUserAnswer.text.isNullOrEmpty()) {
                    currentPosition++
                    if (currentPosition <= questionsList.size) {
                        setQuestion()
                    } else {
                         if (it != null) { Navigation.findNavController(it).navigate(R.id.fromEditToResult) }
                    }
                } else{
                    binding.edUserAnswer.isEnabled = false
                    val userAnswer = binding.edUserAnswer.text.toString().toInt()
                    val question = questionsList[currentPosition - 1]
                    if (question.questEditList["correctAnswerEditText"] == userAnswer) {
                        binding.tvUserResultOfAnswer.visibility = View.VISIBLE
                        answerView(1, R.drawable.correct_option_border_bg, getString(R.string.you_correc))
                        unCorrectAnswer++

                    } else {
                        binding.tvUserResultOfAnswer.visibility = View.VISIBLE
                        answerView(1, R.drawable.wrong_option_border_bg, getString(R.string.wrong))
                        correctAnswer++
                    }
                    selectedOptionPosition = 0
                    binding.edUserAnswer.text.clear()
                }
            }

        }
    }
}
