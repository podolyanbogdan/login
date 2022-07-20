package com.example.login.recAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.arch.ext.navigate
import com.example.login.constants.Constants
import com.example.login.constants.Constants.HERO
import com.example.login.constants.Constants.MASTER
import com.example.login.constants.Constants.PLAYER
import com.example.login.data.UserModel
import com.example.login.databinding.PointItemRecBinding
import com.example.login.repository.PreferenceStorage
import com.google.android.material.bottomnavigation.BottomNavigationView

class PointsAdapter(
    private val points: MutableList<UserModel>,
    private val fragment: Fragment,
    private val context: Context
) : RecyclerView.Adapter<PointsAdapter.PointsViewHolder>() {
    private lateinit var deleteBtn: ImageView
    private lateinit var characterImage: ImageView


    inner class PointsViewHolder(
        val recyclerPointItemRecBinding: PointItemRecBinding,
    ) : RecyclerView.ViewHolder(recyclerPointItemRecBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PointsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.point_item_rec,
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: PointsViewHolder, position: Int) {
        holder.recyclerPointItemRecBinding.data = points[position]
        deleteBtn = holder.itemView.findViewById(R.id.imgDelete)
        characterImage = holder.itemView.findViewById(R.id.imgStar)
        characterImage.setImageResource(whichImage())

        deleteBtn.setOnClickListener {
            removeAt(position)
        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("focus" to true)
            fragment.navigate(R.id.mapFragment, bundle)
        }

    }

    override fun getItemCount() = points.size

    private fun whichImage(): Int {
        return when (PreferenceStorage(context).getCharacterName(Constants.CHARACTER_NAME_KEY)) {
            HERO -> return R.drawable.hero_icon_rec
            PLAYER -> return R.drawable.player_icon_rec
            MASTER -> return R.drawable.maser_icon_rec
            else -> return R.drawable.ic_character
        }
    }

    private fun removeAt(position: Int) {
        points.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, points.size)
    }
}