package com.example.login.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.forEachIndexed
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.login.R
import com.example.login.databinding.FragmentGameBinding
import com.example.login.viewmodels.CellState
import com.example.login.viewmodels.GameStatus
import com.example.login.viewmodels.GameViewModel
import com.example.login.viewmodels.TurnsViewModel

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()
    val args: GameFragmentArgs by navArgs()

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
        val resultNavArgs = args.isCompFirst
        val playerPlayerArgs = args.playerPlayerMode
        if(resultNavArgs){ viewModel.compOnCellClick() }
        binding.btnStartGame.setOnClickListener {
            viewModel.onReloadClick()
            if(resultNavArgs){ viewModel.compOnCellClick() }
        }
        if(!playerPlayerArgs){
            binding.field.forEachIndexed { index, view ->
                view.setOnClickListener { viewModel.onCellClick(index) }
            }
        } else {
            binding.field.forEachIndexed { index, view ->
                view.setOnClickListener { viewModel.playerPlayerGame(index) }
            }
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
        moveBack()
    }

    fun moveBack(){
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    view?.let { Navigation.findNavController(it).navigate(R.id.BackFromMainGameToGameMode) }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback
        )
    }

}