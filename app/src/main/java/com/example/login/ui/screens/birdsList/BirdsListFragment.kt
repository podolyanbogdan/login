package com.example.login.ui.screens.birdsList

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.BirdModel
import com.example.login.data.enumss.From
import com.example.login.data.repository.BirdRepository
import com.example.login.databinding.FragmentBirdsListBinding
import com.example.login.ui.screens.birdsList.adapter.BirdsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirdsListFragment : BaseFragment<FragmentBirdsListBinding>(R.layout.fragment_birds_list) {
    override val viewModel: BirdsListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        binding.btnResponse.setOnClickListener {
            Toast.makeText(context, "${viewModel.response.value}", Toast.LENGTH_LONG).show()
        }
        return view
    }

    private fun initRecycler() {
        binding.recBirds.also { rec ->
            rec.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rec.adapter = BirdsAdapter(
                birdsList = viewModel.birdList.value as List<BirdModel>,
                showMore = { viewModel.showMore(it) }
            )
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.showMoreTrigger.observe(this) {
            navigate(R.id.detailsFragment)
        }
        viewModel.birdList.observe(this){
            if(!it.isNullOrEmpty()){
                initRecycler()
                viewModel.showLoadingGif.value = true
            }
        }
    }
}