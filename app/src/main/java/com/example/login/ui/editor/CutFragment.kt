package com.example.login.ui.editor

import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.Actions
import com.example.login.databinding.FragmentCutBinding
import com.example.login.imageUtils.ImageUtils
import com.example.login.repository.ImageRepository
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.FileDescriptor
import java.io.IOException
import java.util.*


class CutFragment : BaseFragment<FragmentCutBinding>(R.layout.fragment_cut) {
    override val viewModel: CutViewModel by viewModel()
    private lateinit var bitToSave: Bitmap
    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        editImage()
        changeContrast()
        changeBrightness()
        BW1()
        BW2()
        BW3()
        BW4()
        BW5()
        Toast.makeText(context, ImageRepository().takeCurrentImagePath(), Toast.LENGTH_SHORT).show()
        cropActivityResultLauncher.launch(null)
        return view
    }

    //convert uti to bitmap for saving
    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor =
                requireActivity().contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            bitToSave = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return bitToSave
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    //load uri to imageView and save it to gallery
    private fun editImage() {
        cropActivityResultLauncher =
            registerForActivityResult(
                ImageUtils(
                    requireContext(),
                    requireActivity()
                ).cropActivityResultContract
            ) {
                it?.let { uri ->
                    uriToBitmap(uri)
                    binding.imgToEdit.setImageURI(uri)
                    initBWButtons()
                }
            }
    }

    private fun changeBrightness() {
        binding.seekBrightness.progress = 50
        binding.seekBrightness.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.imgToEdit.brightness = (progress / 100.0f) * 2
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun changeContrast() {
        binding.seekSaturation.progress = 50
        binding.seekSaturation.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.imgToEdit.saturation = (progress / 100.0f) * 2
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun BW1() {
        binding.btnBW1.setOnClickListener {
            setBW(binding.imgToEdit, 0F, 0.22f)
        }
    }

    private fun BW2() {
        binding.btnBW2.setOnClickListener {
            setBW(binding.imgToEdit, 10F, 0.44F)
        }
    }

    private fun BW3() {
        binding.btnBW3.setOnClickListener {
            setBW(binding.imgToEdit, 20F, 0.15F)
        }
    }

    private fun BW4() {
        binding.btnBW4.setOnClickListener {
            setBW(binding.imgToEdit, 30F, 0.20F)
        }
    }

    private fun BW5() {
        binding.btnBW5.setOnClickListener {
            setBW(binding.imgToEdit, 5F, 0.40F)
        }
    }

    private fun setBW(iv: ImageView, brightness: Float, type: Float) {
        val colorMatrix = floatArrayOf(
            type, type, type, 0f, brightness,
            type, type, type, 0f, brightness,
            type, type, type, 0f, brightness,
            0f, 0f, 0f, 1f, 0f
        )
        val colorFilter: ColorFilter = ColorMatrixColorFilter(colorMatrix)
        iv.colorFilter = colorFilter
    }

    private fun initBWButtons() {
        setBW(binding.btnBW1, 0F, 0.22f)
        setBW(binding.btnBW2, 10F, 0.44F)
        setBW(binding.btnBW3, 20F, 0.15F)
        setBW(binding.btnBW4, 30F, 0.20F)
        setBW(binding.btnBW5, 5F, 0.40F)
    }

    private fun undoImage() {
        val bmImg =
            BitmapFactory.decodeFile("/storage/emulated/0/Pictures/${ImageRepository().takeCurrentImagePath()}.jpg")
        Toast.makeText(context, "take: ${ImageRepository().takeCurrentImagePath()} ", Toast.LENGTH_SHORT).show()
        binding.imgToEdit.setImageBitmap(bmImg)
    }

    //just observers
    override fun setObservers() {
        super.setObservers()
        viewModel.actions.observe(this) { act ->
            when (act) {
                Actions.SAVE_PHOTO -> {

                }
                Actions.HOME -> {
                    navigate(R.id.homeFragment)
                }
                Actions.RESET -> {
                    binding.seekSaturation.progress = 50
                    binding.seekBrightness.progress = 50
                    binding.imgToEdit.brightness = 1.0F
                    binding.imgToEdit.saturation = 1.0F
                }
                Actions.UNDO -> {
                    undoImage()
                }
                Actions.SAVE_CHANGES -> {
                    val path = UUID.randomUUID().toString()
                    ImageUtils(
                        requireContext(),
                        requireActivity()
                    ).savePhotoToExternalStorage(path, bitToSave)
                    ImageRepository().saveCurrentImagePath(path)
                    Toast.makeText(context, "save: $path", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
