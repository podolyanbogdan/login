package com.example.login.ui.mainScreens.permissionScreen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.arch.ext.navigate
import com.example.login.data.enumss.PermissionStatus
import com.example.login.databinding.FragmentPermissionAndLocaleBinding
import com.example.login.ui.mainScreens.home.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class PermissionAndLocaleFragment :
    BaseFragment<FragmentPermissionAndLocaleBinding>(R.layout.fragment_permission_and_locale) {

    override val viewModel: PermissionLocaleViewModel by viewModel()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
        return view
    }




    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener { location ->
            viewModel.setLongitude(location.longitude.toBits())
            viewModel.setLatitude(location.latitude.toBits())
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.locationTrigger.observe(this) { status ->
            if (status == true) {
                fetchLocation()
                navigate(PermissionAndLocaleFragmentDirections.actionPermissionAndLocaleFragmentToBottomAppBarActivity())
            }
        }
    }
}
