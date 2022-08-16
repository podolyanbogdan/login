package com.example.login.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enums.Direction
import com.example.login.data.enums.PermissionStatus
import com.example.login.databinding.FragmentHomeBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        return view
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.direction.observe(this) { dir ->
            when (dir) {
                Direction.EDIT -> {
                    cameraCheckPermission()
                }
                Direction.SETTINGS -> {
                    navigate(R.id.settingsDialogFragment)
                }
            }
        }
        viewModel.permissionStatus.observe(this){ status ->
            when(status){
                PermissionStatus.GRANTED -> navigate(R.id.editorFragment)
                PermissionStatus.NEXT_PERMISSION -> galleryCheckPermission()
                PermissionStatus.DENIED -> navigate(R.id.settingsDialogFragment)
            }
        }
    }

    private fun galleryCheckPermission() {
        Dexter.withContext(requireContext()).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                viewModel.setPermissionStatus(PermissionStatus.GRANTED)
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                viewModel.setPermissionStatus(PermissionStatus.DENIED)
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?, p1: PermissionToken?
            ) {
                viewModel.setPermissionStatus(PermissionStatus.DENIED)
            }
        }).onSameThread().check()
    }

    private fun cameraCheckPermission() {
        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(
                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        viewModel.setPermissionStatus(PermissionStatus.NEXT_PERMISSION)
                    }
                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                        viewModel.setPermissionStatus(PermissionStatus.DENIED)
                    }

                }
            ).onSameThread().check()
    }
}
