package com.example.login.ui.wifi

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.login.R
import com.example.login.arch.BaseFragment
import com.example.login.databinding.FragmentWifiBinding
import com.example.login.ui.dialogFragment.DialogViewModel
import com.example.login.ui.dialogFragment.WifiDialogInfoFragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt


class WifiFragment : BaseFragment<FragmentWifiBinding>(R.layout.fragment_wifi) {
    override val viewModel: WifiViewModel by viewModel()
    private val dialogViewModel: DialogViewModel by activityViewModels()
    private val wifiList: ArrayList<View> = arrayListOf()
    private lateinit var myConstrain: ConstraintLayout
    private var angle: Float = 330F
    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation
    private val FINE_LOCATION_RQ = 101
    private val location = "location"
    val new: MutableSet<View> = mutableSetOf()
    val old: MutableSet<View> = mutableSetOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_wifi)
        outAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_destroy_wifi)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        myConstrain = binding.mainContainer
        checkForPermission(Manifest.permission.ACCESS_FINE_LOCATION, location, FINE_LOCATION_RQ)
        return view
    }

    private fun checkForPermission(permission: String, name: String, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        permission
                    )
                } == PackageManager.PERMISSION_GRANTED -> {
                    view?.let {
                        Snackbar.make(
                            it,
                            name + " " + getString(R.string.refused),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(
                    permission,
                    name,
                    requestCode
                )
                else -> ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(permission),
                    requestCode
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                view?.let {
                    Snackbar.make(
                        it,
                        name + " " + getString(R.string.refused),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                view?.let {
                    Snackbar.make(
                        it,
                        name + " " + getString(R.string.refused),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        when (requestCode) {
            FINE_LOCATION_RQ -> innerCheck(location)
        }
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setMessage(getString(R.string.perm_access) + name)
            setTitle(getString(R.string.granted1))
            setPositiveButton(getString(R.string.ok)) { dialog, which ->
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(permission),
                    requestCode
                )
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun setObservers() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.wifiList.observe(this) {
                deleteItems()
                createWifi(it, requireContext())
                wifiAnimation()
            }
        }, 2000)
        Handler(Looper.getMainLooper()).postDelayed({
            setObservers()
        }, 6000)
    }


    private fun wifiAnimation() {
        val animAlpha = AnimationUtils.loadAnimation(context, R.anim.button_anim);
        binding.tvWifiScan.startAnimation(animAlpha);
    }

    private fun createWifi(wifiList: ArrayList<WifiModel>, context: Context) {
        var bssidList = mutableListOf<String>()
        wifiList.forEach { wifiObject ->
            val oneWifiItem = layoutInflater.inflate(R.layout.wifi_item, null)
            val wifiName = oneWifiItem.findViewById<TextView>(R.id.tvWifiName)
            val wifiPower = oneWifiItem.findViewById<TextView>(R.id.tvWifiPower)
            val wifiBssid = oneWifiItem.findViewById<TextView>(R.id.tvBssid)
            myConstrain.addView(oneWifiItem)

            val layoutWidth = myConstrain.width
            val name = wifiObject.scanResult.SSID
            val power = wifiObject.scanResult.level
            val descr = wifiObject.scanResult.capabilities
            val bssid = wifiObject.scanResult.BSSID
            val params = oneWifiItem.layoutParams as ConstraintLayout.LayoutParams
            bssidList.add(bssid)




            when {
                power > -50 -> {
                    wifiPower.text = getString(R.string.excellent)
                    params.circleRadius = layoutWidth / 5
                    angle += 30F
                }
                power in -50 downTo -70 -> {
                    wifiPower.text = getString(R.string.fair)
                    params.circleRadius = layoutWidth / 3
                    angle += 30F
                }
                power in -71 downTo -100 -> {
                    wifiPower.text = getString(R.string.weak)
                    params.circleRadius = (layoutWidth / 1.9).roundToInt()
                    angle += 10F
                }

            }




            wifiName.text = name
            params.circleConstraint = R.id.tvWifiScan
            params.circleAngle = angle

            this.wifiList.add(oneWifiItem)
            oneWifiItem.startAnimation(inAnimation)
            oneWifiItem.layoutParams = params


            clickOnWifi(oneWifiItem = oneWifiItem, name = name, desc = descr)
        }
    }

    private fun deleteItems() {
        wifiList.forEach { myConstrain.removeView(it) }
        wifiList.clear()
    }

    private fun clickOnWifi(oneWifiItem: View, name: String, desc: String) {
        oneWifiItem.setOnClickListener {
            val dialog = WifiDialogInfoFragment()
            dialogViewModel.wifiName.value = name
            dialogViewModel.wifiDescription.value = desc
            dialog.show(childFragmentManager, "customDialog")
        }
    }

}
