package com.example.login.ui.translated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login.R
import com.example.login.adapters.ResultAdapter
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.databinding.FragmentTranslatedWordBinding
import com.example.login.parser.parserDictionary
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatedWordFragment :
    BaseFragment<FragmentTranslatedWordBinding>(R.layout.fragment_translated_word) {
    override val viewModel: TranslatedWordViewModel by viewModel()
    private val args: TranslatedWordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        backPressed()
        initRecycler()
        return view
    }


    private fun initRecycler() {
        val data = parserDictionary(requireContext(), args.word, args.language)
        viewModel.wordTranslated.value = data
        viewModel.wordTranslated.observe(viewLifecycleOwner) {
            binding.recyclerResult.also {
                it.layoutManager = GridLayoutManager(requireContext(), 2)
                it.adapter = ResultAdapter(data)
            }
        }
    }

    private fun backPressed() {
        viewModel.initEvent.observe(viewLifecycleOwner) {
            if (it) navigate(R.id.translatorFragment)
        }
    }
}
