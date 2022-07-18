package com.example.login.ui.map

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.constants.Constants.AGE_SCREEN
import com.example.login.constants.Constants.CHARACTER_SCREEN
import com.example.login.constants.Constants.LEVEL_SCREEN
import com.example.login.constants.Constants.LOCATION_REQ_CODE
import com.example.login.databinding.FragmentMapBinding
import com.example.login.repository.PreferenceStorage
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    override val viewModel: MapViewModel by viewModel()
    private lateinit var myMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var stop = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initMap()
        binding.viewmodel = viewModel
        return view
    }


    override fun setObservers() {
        super.setObservers()
        viewModel.positionClicked.observe(this) {

        }
    }

    private fun initMap() {
        if (PreferenceStorage(requireContext()).checkAgeScreen(AGE_SCREEN)) Toast.makeText(
            requireContext(),
            "AGE CHOSE",
            Toast.LENGTH_SHORT
        ).show()
        if (PreferenceStorage(requireContext()).checkLevelScreen(LEVEL_SCREEN)) Toast.makeText(
            requireContext(),
            "LEVEL CHOSE",
            Toast.LENGTH_SHORT
        ).show()
        if (PreferenceStorage(requireContext()).checkCharacterScreen(CHARACTER_SCREEN)) Toast.makeText(
            requireContext(),
            "CHARACTER CHOSE",
            Toast.LENGTH_SHORT
        ).show()
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }


    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap
        myMap.uiSettings.isZoomControlsEnabled = true
        myMap.setOnMarkerClickListener(this)
        setUpMap()
    }


    private fun statusCheck() {
        val manager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (!manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert: AlertDialog = builder.create()
        alert.show()
    }


    private fun setUpMap() {
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQ_CODE
            )
            setUpMap()
            return
        }
        statusCheck()
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
        markerOptions.title("${currentLatLong.latitude} - ${currentLatLong.longitude}")

        val fakePos1 = LatLng(48.49444212875879, 32.2681212371369)
        val fakeMarker1 = MarkerOptions().position(fakePos1)
        fakeMarker1.title("Fake Dude 1")

        val fakePos2 = LatLng(48.493759571733364, 32.268239254327156)
        val fakeMarker2 = MarkerOptions().position(fakePos2)
        fakeMarker2.title("Fake Dude 2")

        myMap.addMarker(markerOptions)
        myMap.addMarker(fakeMarker1)
        myMap.addMarker(fakeMarker2)
    }

    override fun onMarkerClick(p0: Marker) = false

}