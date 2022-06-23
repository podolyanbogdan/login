package com.example.login.ui.wifi

import android.animation.ValueAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
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
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class WifiFragment : BaseFragment<FragmentWifiBinding>(R.layout.fragment_wifi) {
    override val viewModel: WifiViewModel by viewModel()
    private val dialogViewModel: DialogViewModel by activityViewModels()

    private val wifiList: ArrayList<View> = arrayListOf()
    private lateinit var wifiListModel: ArrayList<WifiModel>

    private lateinit var myConstrain: ConstraintLayout
    private var angle: Float = 0F

    private val FINE_LOCATION_RQ = 101
    private val location = "location"

    private lateinit var inAnimation: Animation
    private lateinit var outAnimation: Animation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_wifi)
        outAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha_destroy_wifi)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        checkForPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, location, FINE_LOCATION_RQ)
        binding.viewmodel = viewModel
        myConstrain = binding.mainContainer
        viewModel.wifiList.observe(viewLifecycleOwner){
            wifiListModel = it
        }
        viewModel.trigger.observe(viewLifecycleOwner) {
            deleteWifiObject()
            createWifi(wifiListModel, requireContext())
            wifiAnimation()
        }
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
                    Toast.makeText(context, name + " " + getString(R.string.granted1), Toast.LENGTH_SHORT).show()
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
                Toast.makeText(context, name + " " + getString(R.string.refused), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, name + " " + getString(R.string.granted1), Toast.LENGTH_SHORT).show()
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
            setPositiveButton("OK") { dialog, which ->
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


    private fun wifiAnimation() {
        val animAlpha = AnimationUtils.loadAnimation(context, R.anim.button_anim);
        binding.tvWifiScan.startAnimation(animAlpha);
    }


    private fun createWifi(wifiList: ArrayList<WifiModel>, context: Context) {
        wifiList.forEach { wifiObject ->
            val oneWifiItem = layoutInflater.inflate(R.layout.wifi_item, null)
            val wifiName = oneWifiItem.findViewById<TextView>(R.id.tvWifiName)
            val wifiPower = oneWifiItem.findViewById<TextView>(R.id.tvWifiPower)
            myConstrain.addView(oneWifiItem)

            wifiName.text = wifiObject.name
            val params = oneWifiItem.layoutParams as ConstraintLayout.LayoutParams
            params.circleConstraint = R.id.tvWifiScan
            params.circleAngle = angle

            when {
                wifiObject.power > -50 -> {
                    wifiPower.text = getString(R.string.excellent)
                    params.circleRadius = 150
                }
                wifiObject.power in -50 downTo -64 -> {
                    wifiPower.text = getString(R.string.good)
                    params.circleRadius = 250
                }
                wifiObject.power in -65 downTo -74 -> {
                    wifiPower.text = getString(R.string.fair)
                    params.circleRadius = 500
                }
                wifiObject.power in -75 downTo -100 -> {
                    wifiPower.text = getString(R.string.weak)
                    params.circleRadius = 650
                }
            }
            this.wifiList.add(oneWifiItem)
            oneWifiItem.startAnimation(inAnimation)
            oneWifiItem.layoutParams = params
            angle += 30F
            clickOnWifi(oneWifiItem, wifiObject.name, wifiObject.description)
        }
    }

    private fun deleteWifiObject() {
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
