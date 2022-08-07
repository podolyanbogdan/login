package com.example.login.data

enum class Actions {
    HOME, SAVE, CROP
}


/*
* package com.example.login.ui.editor

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.registerForActivityResult
import androidx.navigation.fragment.navArgs
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Actions
import com.example.login.data.Direction
import com.example.login.databinding.FragmentEditorBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageActivity
import com.theartofdev.edmodo.cropper.CropImageOptions
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
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
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
*
* */