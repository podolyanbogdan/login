package com.example.login.ui

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentMainQuestionsBinding
import com.example.login.model.Constants
import com.example.login.model.QuestionModel
import com.example.login.viewmodel.UserAuthorizationViewModel


class MainQuestionsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainQuestionsBinding
    private val userAuthorizationViewModel: UserAuthorizationViewModel by activityViewModels()
    private var currentPosition = 1
    private lateinit var questionsList: ArrayList<QuestionModel>
    private var selectedOptionPosition = 0
    private var isClicked = false
    private var correctAnswer = 0
    private var unCorrectAnswer = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainQuestionsBinding.inflate(inflater, container, false)
        questionsList = Constants.getQuestions()
        setQuestion()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun clickableOption(status: Boolean) {
        binding.tvOptionOne.isClickable = status
        binding.tvOptionTwo.isClickable = status
        binding.tvOptionThree.isClickable = status
        binding.tvOptionFour.isClickable = status
    }

    private fun setQuestion() {
        val question = questionsList[currentPosition - 1]

        defaultOptionsView()

        if (currentPosition == questionsList.size) {
            binding.btnSubmit.text = getString(R.string.finish_two)
        } else {
            binding.btnSubmit.text = getString(R.string.submit_btn)
        }

        binding.progressBar.progress = currentPosition
        binding.tvProgress.text =
            "$currentPosition" + getString(R.string.chertochka) + questionsList.size
        binding.tvQuestName.text = question.questionName
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour


        isClicked = false
        clickableOption(true)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#2165D5"))
            option.typeface = Typeface.DEFAULT
            option.background = context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.default_option_border_bg
                )
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(binding.tvOptionOne, 1)
                isClicked = true
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(binding.tvOptionTwo, 2)
                isClicked = true
            }
            R.id.tvOptionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
                isClicked = true
            }
            R.id.tvOptionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
                isClicked = true
            }
            R.id.btnSubmit -> {
                if (!isClicked) {
                    Toast.makeText(context, R.string.need_to_choice, Toast.LENGTH_SHORT).show()
                } else if (isClicked) {
                    clickableOption(false)
                    if (selectedOptionPosition == 0) {
                        currentPosition++
                        if (currentPosition <= questionsList.size) {
                            setQuestion()
                        } else {
                            view.let {
                                Navigation.findNavController(it).navigate(R.id.fromMainQuestToEditQuest)
                            }
                        }
                    } else {
                        val question = questionsList[currentPosition - 1]
                        if (question.correctAnswer != selectedOptionPosition) {
                            answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                            unCorrectAnswer++
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                        correctAnswer++
                        if (currentPosition == questionsList.size) {
                            binding.btnSubmit.text = getString(R.string.next_module)
                        } else {
                            binding.btnSubmit.text = getString(R.string.go_to_next_question)
                        }
                        userAuthorizationViewModel.correctAnswerModel.value = correctAnswer
                        userAuthorizationViewModel.unCorrectAnswerModel.value = unCorrectAnswer
                        selectedOptionPosition = 0
                    }
                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            2 -> {
                binding.tvOptionTwo.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            3 -> {
                binding.tvOptionThree.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
            4 -> {
                binding.tvOptionFour.background = context?.let {
                    ContextCompat.getDrawable(
                        it,
                        drawableView
                    )
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        selectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.default_option_border_bg
            )
        }
    }

}