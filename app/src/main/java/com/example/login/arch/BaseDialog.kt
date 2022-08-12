package com.example.login.arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.login.arch.ext.hideKeyboard
import com.google.android.material.snackbar.Snackbar

abstract class BaseDialog<T : ViewDataBinding>(@LayoutRes private val resId: Int) : DialogFragment() {

    protected lateinit var binding: T
        private set

    protected abstract val viewModel: BaseViewModel

    protected open fun setObservers() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setObservers()
        onBaseObservers()
    }

    private fun onBaseObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { hideKeyboard() }
        viewModel.errorEvent.observe(viewLifecycleOwner) { showSnack(it) }
    }

    protected fun showSnack(msg: String) {
        Snackbar.make(this.requireView(), msg, Snackbar.LENGTH_SHORT).show()
    }

}