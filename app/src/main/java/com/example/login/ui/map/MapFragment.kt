package com.example.login.ui.map

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.constants.Constants.CHARACTER_NAME_KEY
import com.example.login.constants.Constants.HERO
import com.example.login.constants.Constants.LABEL
import com.example.login.constants.Constants.MASTER
import com.example.login.constants.Constants.PLAYER
import com.example.login.databinding.FragmentMapBinding
import com.example.login.json.JsonConverter
import com.example.login.permissions.PermissionLoc
import com.example.login.repository.MyRepository
import com.example.login.repository.PreferenceStorage
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    override val viewModel: MapViewModel by viewModel()
    private lateinit var myMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var stop = false
    private lateinit var clipboard: ClipboardManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initMap()
        showGamer()
        focusToMarker()
        binding.viewmodel = viewModel
        return view
    }

    private fun focusToMarker(){
        val arg = arguments?.getBoolean("focus")
        if (arg == true) {
            Handler(Looper.getMainLooper()).postDelayed({
                moveToPosition()
            }, 500)
        }

    }

    private fun Context.copyToClipboard(clipLabel: String, text: CharSequence) {
        clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)!!
        clipboard.setPrimaryClip(ClipData.newPlainText(clipLabel, text))
    }

    private fun pasteClipboard(): String {
        return clipboard.primaryClip?.getItemAt(0)?.text.toString()
    }


    private fun showGamer() {
        binding.btnCurrPos.setOnClickListener {
            requireContext().copyToClipboard(
                LABEL,
                JsonConverter(PreferenceStorage(requireContext())).jsonString
            )
            shareMyPosition()
        }
    }

    private fun shareMyPosition() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, pasteClipboard())
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }


    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        myMap.uiSettings.isZoomControlsEnabled = true
        myMap.setOnMarkerClickListener(this)
        PermissionLoc().setUpMap(requireActivity(), requireActivity())
    }

    private fun moveToPosition() {
        myMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                if (!stop) placeMarkerOnMap(currentLatLong)
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 16f))
                stop = true
            }
        }

    }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title(MyRepository(requireContext()).splitUserData())
        markerOptions.snippet("${currentLatLong.longitude} - ${currentLatLong.latitude}")
        markerOptions.icon(requireContext().bitmapDescriptorFromVector(whichImage()))
        myMap.addMarker(markerOptions)
    }

    private fun whichImage(): Int {
        return when (PreferenceStorage(requireContext()).getCharacterName(CHARACTER_NAME_KEY)) {
            HERO -> return R.drawable.hero_icon1
            PLAYER -> return R.drawable.hero_icon2
            MASTER -> return R.drawable.hero_icon3
            else -> return R.drawable.ic_character
        }
    }

    private fun Context.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        vectorDrawable.draw(Canvas(bitmap))
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onMarkerClick(p0: Marker) = false
}