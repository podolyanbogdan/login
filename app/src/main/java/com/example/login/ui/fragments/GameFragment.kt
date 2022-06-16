package com.example.login.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.forEachIndexed
import androidx.fragment.app.viewModels
import com.example.login.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStartGame.setOnClickListener {
            viewModel.onReloadClick()
        }
        binding.field.forEachIndexed { index, view ->
            view.setOnClickListener { viewModel.onCellClick(index) }
        }
        viewModel.currentMove.observe(viewLifecycleOwner) {
            binding.arrow.animate()
                .rotation(if (it == CellState.Cross) 0f else 180f)
        }
        viewModel.states.observe(viewLifecycleOwner){
            val (status, matrix) = it
            matrix.forEachIndexed { index, state ->
                with(binding.field.getChildAt(index) as ImageView) {
                    setImageResource(state.icon)
                    isEnabled = state.isClickable && status == GameStatus.Started
                }
            }
        }
        viewModel.cellStateByIndex.observe(viewLifecycleOwner) {
            val (index, state) = it
            with(binding.field.getChildAt(index) as ImageView) {
                isEnabled = state.isClickable
                setImageResource(state.icon)
            }
        }
        viewModel.winState.observe(viewLifecycleOwner){
            binding.field.drawWinLine(it)
        }
    }
}