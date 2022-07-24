package com.example.login.ui.map

import android.content.*
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.constants.Constants.LABEL
import com.example.login.constants.Constants.SHARE_INTENT_TYPE
import com.example.login.databinding.FragmentMapBinding
import com.example.login.json.JsonConverter
import com.example.login.repository.MyRepository
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
    private lateinit var clipboard: ClipboardManager
    var stop = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initMap()
        showGamer()
        binding.viewmodel = viewModel
        return view
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
            requireContext().copyToClipboard(LABEL, JsonConverter(requireContext()).jsonString)
            shareMyPosition()
        }
    }

    private fun shareMyPosition() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, pasteClipboard())
            type = SHARE_INTENT_TYPE
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun initMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        Handler(Looper.getMainLooper()).postDelayed({
            moveToPosition()
        }, 500)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        myMap.uiSettings.isZoomControlsEnabled = true
        myMap.setOnMarkerClickListener(this)
        GpsAlert(requireContext()).statusCheck()
    }


    private fun moveToPosition() {
        myMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                MyRepository(requireContext()).saveLocation(currentLatLong)
                if(!stop){
                    val init = MyRepository(requireContext()).getJsonModel()
                    MyRepository(requireContext()).markersQuantity(init)
                }
                stop = true
                MarkerCreator(requireContext()).placeMarkerOnMap(currentLatLong, myMap)
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 16f))
            }
        }
    }

    override fun onMarkerClick(p0: Marker) = false
}