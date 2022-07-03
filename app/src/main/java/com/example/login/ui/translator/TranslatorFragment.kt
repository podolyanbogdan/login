package com.example.login.ui.translator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.adapters.HistoryAdapter
import com.example.login.adapters.ResultAdapter
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentTranslatorBinding
import com.example.login.room.Word
import com.example.login.constants.*
import com.example.login.parser.parserDictionary
import com.example.login.ui.translated.TranslatedWordViewModel
import org.jetbrains.anko.doAsync
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private var currentLanguage = "ua"
class TranslatorFragment : BaseFragment<FragmentTranslatorBinding>(R.layout.fragment_translator) {
    override val viewModel: TranslatorViewModel by viewModel()
    private var idCurr: Long = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        choiceLanguage()
        loadPref()
        showTranslate()
        initRecycler()
        binding.viewmodel = viewModel
        return view
    }

    private fun initRecycler() {
        viewModel.allWord.observe(viewLifecycleOwner) { words ->
            binding.recyclerTranslatorHistory.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.adapter = HistoryAdapter(words)
            }
        }
    }


    private fun showTranslate() {
        viewModel.clickTranslate.observe(viewLifecycleOwner) {
            val edWord = binding.edTranslateWord.text.toString().lowercase(Locale.getDefault())
            if (edWord.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_data), Toast.LENGTH_SHORT).show()
            } else {
                doAsync {
                    viewModel.insert(Word(idCurr,edWord,showTime()))
                    idCurr++
                }
                navigate(edWord, currentLanguage)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.delete()
    }


    private fun navigate(word: String, language: String){
        val action = TranslatorFragmentDirections.fromTransToResult(word,language)
        view?.let { it -> Navigation.findNavController(it).navigate(action) }
    }
    private fun showTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.patern))
        return current.format(formatter)
    }
    private fun choiceLanguage() {
        val sharedPreferences =
            context?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        viewModel.choiceLangTrigger.observe(viewLifecycleOwner) {
            if (it) {
                val languages = arrayOf("Ukrainian", "Polska")
                val langSelectorBuilder = context?.let { AlertDialog.Builder(it) }
                langSelectorBuilder?.setTitle(getString(R.string.choice_lang))
                langSelectorBuilder?.setSingleChoiceItems(languages, -1) { dialog, selection ->
                    when (selection) {
                        0 -> {
                            editor?.apply {
                                putInt(IMAGE_KEY, R.drawable.ukraine_flag)
                                putString(LANG_KEY, "ua")
                            }?.apply()
                            binding.imageView.setImageResource(R.drawable.ukraine_flag)
                            currentLanguage = "ua"
                        }
                        1 -> {
                            editor?.apply {
                                putInt(IMAGE_KEY, R.drawable.poland_flag)
                                putString(LANG_KEY, "pol")
                            }?.apply()
                            binding.imageView.setImageResource(R.drawable.poland_flag)
                            currentLanguage =  "pol"
                        }
                    }
                    dialog.dismiss()
                }
                langSelectorBuilder?.create()?.show()
            }
        }
        viewModel.choiceLangTrigger.value = false
    }
    private fun loadPref() {
        val sharedPreferences =
            context?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val savedDrawable = sharedPreferences?.getInt(IMAGE_KEY, R.drawable.ukraine_flag)
        val savedLanguage = sharedPreferences?.getString(LANG_KEY, "ua")
        savedDrawable?.let { binding.imageView.setImageResource(it) }
        savedLanguage?.let { currentLanguage = it }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_STATE, currentLanguage)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            currentLanguage = savedInstanceState.getString(KEY_STATE, "ua")
        }
    }

}