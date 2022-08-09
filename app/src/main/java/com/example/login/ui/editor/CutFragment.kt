package com.example.login.ui.editor

import android.content.ContentValues
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.example.login.data.BWTypes
import com.example.login.databinding.FragmentCutBinding
import com.example.login.imageUtils.ImageUtils
import com.example.login.repository.ImageRepository
import com.example.login.repository.initCanvas
import com.example.login.repository.sdk29AndUp
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.FileDescriptor
import java.io.IOException
import java.util.*


class CutFragment : BaseFragment<FragmentCutBinding>(R.layout.fragment_cut) {
    override val viewModel: CutViewModel by viewModel()
    private var repo = ImageRepository()

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

        initBWBtns()
        cropActivityResultLauncher.launch(null)
        return view
    }

    private fun initBWBtns(){
        setBlackWhiteToImages(binding.btnBW1, BWTypes.TYPES1)
        setBlackWhiteToImages(binding.btnBW2, BWTypes.TYPES2)
        setBlackWhiteToImages(binding.btnBW3, BWTypes.TYPES3)
        setBlackWhiteToImages(binding.btnBW4, BWTypes.TYPES4)
        setBlackWhiteToImages(binding.btnBW5, BWTypes.TYPES5)
    }

    //convert uti to bitmap for saving
    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor =
                requireActivity().contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            repo.bitToSave = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return repo.bitToSave
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    //load uri to imageView and save it to gallery
    private fun editImage() {
        cropActivityResultLauncher =
            registerForActivityResult(
                ImageUtils(requireContext()).cropActivityResultContract
            ) {
                it?.let { uri ->
                    binding.imgToEdit.setImageBitmap(uriToBitmap(uri))
                }
            }
    }


    private fun changeBrightness() {
        binding.seekBrightness.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val brightness = progress.toFloat() - 200
                val contrast = binding.seekSaturation.progress.toFloat() / 10F

                binding.imgToEdit.setImageBitmap(
                    changeBitmapContrastBrightness(
                        brightness = brightness,
                        contrast = contrast,
                    )
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun setBlackWhiteToImages(img: ImageView, type: BWTypes) {
        val colorMatrix = floatArrayOf(
            type.type, type.type, type.type, 0f, type.brigh,
            type.type, type.type, type.type, 0f, type.brigh,
            type.type, type.type, type.type, 0f, type.brigh,
            0f, 0f, 0f, 1f, 0f,
        )

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        img.colorFilter = colorFilter
    }


    private fun changeContrast() {
        binding.seekSaturation.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val brightness = binding.seekBrightness.progress.toFloat() - 200
                val contrast = progress.toFloat() / 10F

                binding.imgToEdit.setImageBitmap(
                    changeBitmapContrastBrightness(
                        brightness = brightness,
                        contrast = contrast,
                    )
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }


    fun changeBitmapContrastBrightness(contrast: Float, brightness: Float): Bitmap? {
        repo.lastColorMatrix = ColorMatrix(
            floatArrayOf(
                contrast, 0f, 0f, 0f, brightness,
                0f, contrast, 0f, 0f, brightness,
                0f, 0f, contrast, 0f, brightness,
                0f, 0f, 0f, 1f, 0f
            )
        )
        return initCanvas(repo)
    }


    private fun changeBitmapBlackWhite(type: BWTypes): Bitmap? {
        repo.lastColorMatrix = ColorMatrix(
            floatArrayOf(
                type.type, type.type, type.type, 0f, type.brigh,
                type.type, type.type, type.type, 0f, type.brigh,
                type.type, type.type, type.type, 0f, type.brigh,
                0f, 0f, 0f, 1f, 0f
            )
        )
        return initCanvas(repo)
    }


    // save image to gallery
    private fun Bitmap.savePhotoToExternalStorage(displayName: String): Boolean {
        val bitmap = copy(Bitmap.Config.ARGB_8888, true)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(repo.lastColorMatrix)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bitmap, 0F, 0F, paint)

        val imageCollection = sdk29AndUp {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
        }
        return try {
            requireActivity().contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                requireActivity().contentResolver.openOutputStream(uri).use { outputStream ->
                    if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                        throw IOException(getString(R.string.couldnt_save_bitmap))
                    }
                }
            } ?: throw IOException(getString(R.string.couldnt_create_mediastore_entry))
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }


    private fun undoImage() {
        val bmImg =
            BitmapFactory.decodeFile("/storage/emulated/0/Pictures/${ImageRepository().takeCurrentImagePath()}.jpg")
        binding.imgToEdit.setImageBitmap(bmImg)
    }

    private fun applyChanges() {
        val path = UUID.randomUUID().toString()
        ImageRepository().saveCurrentImagePath(path)
        repo.bitToSave.savePhotoToExternalStorage(path)
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
                    binding.seekSaturation.progress = 100
                    binding.seekBrightness.progress = 10
                }
                Actions.UNDO -> {
                    undoImage()
                }
                Actions.SAVE_CHANGES -> {
                    applyChanges()
                }
            }
        }
        viewModel.bwTypes.observe(this){ types ->
            when(types){
                BWTypes.TYPES1 -> {
                    binding.imgToEdit.setImageBitmap(
                        changeBitmapBlackWhite(
                            type = BWTypes.TYPES1,
                        )
                    )
                }
                BWTypes.TYPES2 -> {
                    binding.imgToEdit.setImageBitmap(
                        changeBitmapBlackWhite(
                            type = BWTypes.TYPES2,
                        )
                    )
                }
                BWTypes.TYPES3 -> {
                    binding.imgToEdit.setImageBitmap(
                        changeBitmapBlackWhite(
                            type = BWTypes.TYPES3,
                        )
                    )
                }
                BWTypes.TYPES4 -> {
                    binding.imgToEdit.setImageBitmap(
                        changeBitmapBlackWhite(
                            type = BWTypes.TYPES4,
                        )
                    )
                }
                BWTypes.TYPES5 -> {
                    binding.imgToEdit.setImageBitmap(
                        changeBitmapBlackWhite(
                            type = BWTypes.TYPES5,
                        )
                    )
                }
            }
        }
    }
}
