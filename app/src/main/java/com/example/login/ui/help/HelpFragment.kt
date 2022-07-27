package com.example.login.ui.help

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentHelpBinding
import com.skydoves.balloon.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : BaseFragment<FragmentHelpBinding>(R.layout.fragment_help){

    override val viewModel: HelpViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        initBalloons()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.parent, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }


    private fun createBalloon(text: String): Balloon {
        return Balloon.Builder(requireContext())
            .setWidth(300)
            .setHeight(80)
            .setText(text)
            .setTextColorResource(R.color.primary_white_def)
            .setTextSize(15f)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(12)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.green)
            .setBalloonHighlightAnimation(BalloonHighlightAnimation.SHAKE)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

    }

    private fun initBalloons(){
        with(binding) {
            floatingActionButton.showAlignTop(createBalloon(getString(R.string.add_notes)))
            imgExpandContent.showAlignBottom(createBalloon(getString(R.string.expand_content)))
            imgEditNotes.showAlignBottom(createBalloon(getString(R.string.edit_notes)))
            imSwipe.showAlignBottom(createBalloon(getString(R.string.swipe_to_delete)))
        }
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        val menu = toolbar.findViewById<View>(R.id.sortMenu)
        menu.setOnClickListener {}
        menu.showAlignBottom(createBalloon(getString(R.string.sort_notes)))
    }

}