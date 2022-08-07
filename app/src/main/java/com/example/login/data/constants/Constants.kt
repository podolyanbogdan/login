package com.example.login.data.constants

object Constants {
    const val CROP_REQUEST_CODE = 0
    const val CAMERA_REQUEST_CODE = 1
    const val GALLERY_REQUEST_CODE = 2
    const val PACKAGE_TAG = "package"
}

/*
* package com.example.login.ui.editor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Actions
import com.example.login.data.Direction
import com.example.login.data.constants.Constants
import com.example.login.databinding.FragmentEditorBinding
import com.example.login.ui.alert.PermAlert
import com.example.login.ui.home.HomeViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.theartofdev.edmodo.cropper.CropImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditorFragment : BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor) {
    override val viewModel: EditorViewModel by viewModel()
    private val navArgs by navArgs<EditorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel

        editFromGallery()
        editFromCamera()

        return view
    }

    private fun editFromGallery() {
        if (navArgs.direction == Direction.GALLERY) {
            galleryCheckPermission()
        }
    }

    private fun editFromCamera() {
        if (navArgs.direction == Direction.CAMERA) {
            cameraCheckPermission()
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.actions.observe(this) { act ->
            when (act) {
                Actions.SAVE -> {

                }
                Actions.HOME -> {
                    navigate(R.id.homeFragment)
                }
            }
        }
    }












    private fun galleryCheckPermission() {
        Dexter.withContext(requireContext()).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                PermAlert(requireContext(), requireActivity()).showRotationalDialogForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?, p1: PermissionToken?
            ) {
                PermAlert(requireContext(), requireActivity()).showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.GALLERY_REQUEST_CODE)
    }


    private fun cameraCheckPermission() {
        Dexter.withContext(requireContext())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(
                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {
                            if (report.areAllPermissionsGranted()) {
                                camera()
                            }
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                        PermAlert(
                            requireContext(),
                            requireActivity()
                        ).showRotationalDialogForPermission()
                    }

                }
            ).onSameThread().check()
    }

    private fun launchImageCrop(uri: Uri){
        Toast.makeText(context, "launchImageCrop", Toast.LENGTH_SHORT).show()
        CropImage.activity(uri).start(requireActivity())
    }
    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constants.CAMERA_REQUEST_CODE -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding.imgToEdit.load(bitmap)
                }
                Constants.GALLERY_REQUEST_CODE -> {
                    binding.imgToEdit.load(data?.data)
                }
            }
        }
    }
}*/


        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        *
        * package com.example.login.ui.editor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.navigation.fragment.navArgs
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Actions
import com.example.login.data.Direction
import com.example.login.databinding.FragmentEditorBinding
import com.theartofdev.edmodo.cropper.CropImage
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditorFragment : BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor) {
    override val viewModel: EditorViewModel by viewModel()
    private val navArgs by navArgs<EditorFragmentArgs>()

    private val cropActivityResultContract = object : ActivityResultContract<Any?, Uri?>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .getIntent(requireContext())

        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            CropImage.isExplicitCameraPermissionRequired(requireContext())
            return CropImage.getActivityResult(intent).uri
        }

    }

    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        editFromCamera()
        editFromGallery()
        cropActivityResultLauncher.launch(null)
        return view
    }

    private fun editFromGallery() {
        if (navArgs.direction == Direction.GALLERY) {
            cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract) {
                it?.let { uri ->
                    binding.imgToEdit.setImageURI(uri)
                }
            }
        }
    }

    private fun editFromCamera() {
        if (navArgs.direction == Direction.CAMERA) {
            cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract) {
                it?.let { uri ->
                    binding.imgToEdit.setImageURI(uri)
                }
            }
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.actions.observe(this) { act ->
            when (act) {
                Actions.SAVE -> {

                }
                Actions.HOME -> {
                    navigate(R.id.homeFragment)
                }
                Actions.CROP -> {
                    cropActivityResultLauncher.launch(null)
                }
            }
        }
    }
}
 */