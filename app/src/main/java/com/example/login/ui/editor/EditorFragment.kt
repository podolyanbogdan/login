package com.example.login.ui.editor

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Actions
import com.example.login.databinding.FragmentEditorBinding
import com.theartofdev.edmodo.cropper.CropImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileDescriptor
import java.io.IOException


class EditorFragment : BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor) {
    override val viewModel: EditorViewModel by viewModel()
    private lateinit var bitToSave: Bitmap

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
        editImage()
        cropActivityResultLauncher.launch(null)
        return view
    }



    private fun editImage() {
        cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract) {
            it?.let { uri ->
                uriToBitmap(uri)
                binding.imgToEdit.setImageURI(uri)
            }
        }
    }

    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor = requireActivity().contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            bitToSave = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return bitToSave
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun Context.saveBitmap(fileName: String, bitmap: Bitmap){
        val file = File(filesDir, fileName)
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show()
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.actions.observe(this) { act ->
            when (act) {
                Actions.SAVE -> {
                    requireContext().saveBitmap("myFile.png", bitToSave)
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