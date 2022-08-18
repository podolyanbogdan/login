package com.example.login.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.MusicStatus
import com.example.login.databinding.FragmentDetailsBinding
import com.example.login.music.BirdMusic
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    override val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {}
        binding.viewmodel = viewModel
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        BirdMusic.stopMusic()
    }

    override fun onStop() {
        super.onStop()
        BirdMusic.stopMusic()
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.onCloseTrigger.observe(this) {
            navigate(R.id.birdsListFragment)
        }
        viewModel.musicStatus.observe(this) { status ->
            when (status) {
                MusicStatus.PLAY -> {
                    BirdMusic.startMusic(requireContext(), viewModel.birdDetailModel.file)
                }
                MusicStatus.STOP -> {
                    BirdMusic.stopMusic()
                }
                MusicStatus.DOWNLOAD ->{
                    BirdMusic.downloadMusic(
                        viewModel.birdDetailModel.file,
                        viewModel.birdDetailModel.fileName,
                        requireActivity()
                    )
                }
            }
        }
    }

}