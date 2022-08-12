package com.example.login.ui.editor

import android.content.ContentValues
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enums.Actions
import com.example.login.data.models.BWModel
import com.example.login.data.enums.BitmapStates
import com.example.login.data.constants.Constants.CACHE_IMAGE
import com.example.login.data.constants.Constants.DEF_BRIGHT_VALUE
import com.example.login.data.constants.Constants.DEF_CONTRAST_VALUE
import com.example.login.data.constants.Constants.IMG_TYPE
import com.example.login.data.constants.Constants.MODE
import com.example.login.databinding.FragmentEditorBinding
import com.example.login.imageCache.ImageCache
import com.example.login.imageUtils.ImageUtils
import com.example.login.recAdapter.BWAdapter
import com.example.login.repository.ImageRepository
import com.example.login.repository.initCanvas
import com.example.login.repository.sdk29AndUp
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.FileDescriptor
import java.io.IOException
import java.util.*


class EditorFragment : BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor) {
    override val viewModel: EditorViewModel by viewModel()
    private var repo = ImageRepository()
    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        editImage()
        initRecycler()
        cropActivityResultLauncher.launch(null)
        return view
    }

    //recycler for bw types
    private fun initRecycler() {
        binding.recyBW.also { rec ->
            rec.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rec.adapter = BWAdapter(
                bwList = viewModel.BWItems.value as List<BWModel>,
                setMatrix = { viewModel.setMatrix(it) })
        }
    }


    //convert uti to bitmap
    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val parcelFileDescriptor =
                requireActivity().contentResolver.openFileDescriptor(selectedFileUri, MODE)
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            repo.bitToSave = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return repo.bitToSave
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }


    //load uri after crop and convert it to bitmap
    private fun editImage() {
        cropActivityResultLauncher =
            registerForActivityResult(
                ImageUtils(requireContext()).cropActivityResultContract
            ) {
                it?.let { uri ->
                    viewModel.saveBit(uriToBitmap(uri))
                }
            }
    }

    private fun changeBitmapContrastBrightness(contrast: Float, brightness: Float): Bitmap? {
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

    private fun changeBitmapBlackWhite(type: ColorMatrix): Bitmap? {
        repo.lastColorMatrix = type
        return initCanvas(repo)
    }

    // save image to gallery
    private fun Bitmap.savePhotoToExternalStorage(): Boolean {
        val uuid = UUID.randomUUID().toString()

        val bitmap = copy(Bitmap.Config.ARGB_8888, true)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(repo.lastColorMatrix)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bitmap, 0F, 0F, paint)

        val imageCollection = sdk29AndUp {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$uuid$IMG_TYPE")
        }
        return try {
            requireActivity().contentResolver.insert(imageCollection, contentValues)?.also { uri ->
                requireActivity().contentResolver.openOutputStream(uri).use { outputStream ->
                    if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                        throw IOException(getString(R.string.couldnt_save_bitmap))
                    }
                }
            } ?: throw IOException(getString(R.string.couldnt_create_mediastore_entry))
            viewModel.savedProcessState(BitmapStates.SAVED)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            viewModel.savedProcessState(BitmapStates.ERROR)
            false
        }
    }

    private fun Bitmap.saveImageToCache() {
        val bitmap = copy(Bitmap.Config.ARGB_8888, true)
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(repo.lastColorMatrix)
        val canvas = Canvas(bitmap)
        canvas.drawBitmap(bitmap, 0F, 0F, paint)
        ImageCache.instance.saveBitmapToCahche(CACHE_IMAGE, bitmap)
        viewModel.savedProcessState(BitmapStates.SAVED_CHANGES)
    }

    private fun getImageFromCache() {
        val bmImg = ImageCache.instance.retrieveBitmapFromCache(CACHE_IMAGE)
        viewModel.saveBit(bmImg)
    }


    //just observers
    override fun setObservers() {
        super.setObservers()
        viewModel.actions.observe(this) { act ->
            when (act) {
                Actions.SAVE_PHOTO -> {
                    repo.bitToSave.savePhotoToExternalStorage()
                }
                Actions.HOME -> {
                    navigate(R.id.homeFragment)
                }
                Actions.UNDO -> {
                    getImageFromCache()
                }
                Actions.SAVE_CHANGES -> {
                    repo.bitToSave.saveImageToCache()
                }
            }
        }

        viewModel.brightnessValue.observe(this) { value ->
            val brightness = value.toFloat() - DEF_BRIGHT_VALUE
            val contrast = binding.seekSaturation.progress.toFloat() / DEF_CONTRAST_VALUE
            viewModel.saveBit(
                changeBitmapContrastBrightness(
                brightness = brightness,
                contrast = contrast,
            ))

        }

        viewModel.contrastValue.observe(this) { value ->
            val brightness = binding.seekBrightness.progress.toFloat() - DEF_BRIGHT_VALUE
            val contrast = value.toFloat() / DEF_CONTRAST_VALUE
            viewModel.saveBit(
                changeBitmapContrastBrightness(
                    brightness = brightness,
                    contrast = contrast,
                )
            )
        }

        viewModel.bwTypes.observe(this) { types ->
            viewModel.saveBit(
                changeBitmapBlackWhite(
                    type = types,
                )
            )
        }

        viewModel.savedProcessState.observe(this){ state ->
            when(state){
                BitmapStates.SAVED -> showToast(getString(R.string.your_image_was_save))
                BitmapStates.ERROR -> showToast(getString(R.string.couldnt_save_bitmap))
                BitmapStates.SAVED_CHANGES -> showToast(getString(R.string.changes_have_been_saved))
            }
        }
    }
}