package com.example.login.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.constants.Constants.delete
import com.example.login.constants.Constants.dialog2
import com.example.login.constants.Constants.edit
import com.example.login.constants.Constants.fieldPopup
import com.example.login.constants.Constants.forceIcon
import com.example.login.data.TaskModel
import com.example.login.databinding.RecTaskItemBinding
import com.example.login.repository.TaskRepository
import com.example.login.ui.editRecItem.EditRecItemDialog

class TasksAdapter(
    private val tasks: MutableList<TaskModel>,
    private val context: Context,
    private val fragment: FragmentManager
) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    private lateinit var tvTitle: TextView
    private lateinit var tvTimeBoth: TextView
    private lateinit var dots: ImageButton
    private lateinit var recTags: RecyclerView

    inner class TasksViewHolder(
        val recTaskItem: RecTaskItemBinding
    ) : RecyclerView.ViewHolder(recTaskItem.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TasksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rec_task_item,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.recTaskItem.data = tasks[position]
        dots = holder.itemView.findViewById(R.id.btnDots)
        tvTitle = holder.itemView.findViewById(R.id.tvTitleTask)
        tvTimeBoth = holder.itemView.findViewById(R.id.tvTimeBoth)
        recTags = holder.itemView.findViewById(R.id.recTagsInside)

        initTags()
        dots.setOnClickListener {
            showPopup(dots, position)
        }
    }

    override fun getItemCount() = tasks.size

    private fun showPopup(dots: ImageButton, position: Int) {
        val popupMenu = PopupMenu(context, dots)
        popupMenu.inflate(R.menu.rec_dots_main)
        popupMenu.show()
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField(fieldPopup)
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod(forceIcon, Boolean::class.java)
                .invoke(mPopup, true)
        } finally {
            popupMenu.show()
        }
        popupMenu.setOnMenuItemClickListener { item ->
            if (item.title == delete) {
                removeAt(position)
            }
            if (item.title == edit) {
                editAt(position)
            }
            true
        }
    }

    private fun removeAt(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, tasks.size)
    }

    private fun editAt(position: Int) {
        EditRecItemDialog(
            titleRes = { data ->
                tvTitle.text = data
            },
            timeBoth = { data ->
                tvTimeBoth.text = data
            }
        ).show(fragment, dialog2)
        notifyItemChanged(position)
    }

    private fun initTags() {
        recTags.layoutManager = GridLayoutManager(context, 3)
        recTags.adapter = TagsInsideAdapter(TaskRepository.setTagInside())
    }

}