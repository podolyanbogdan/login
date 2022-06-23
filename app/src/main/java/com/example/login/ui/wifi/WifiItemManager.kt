package com.example.login.ui.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager

class WifiItemManager(context: Context) {
    val wifiManager: WifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val resultWifiList: ArrayList<WifiModel> = arrayListOf()
    private val listSize = 16

    private val wifiReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val wifiScanList: List<ScanResult> = wifiManager.scanResults
            updateList(wifiScanList)
        }
    }

    init {
        wifiManager.startScan()
        context.registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
    }


    fun getResultList(): ArrayList<WifiModel> {
        return resultWifiList
    }

    private fun updateList(scanList: List<ScanResult>) {
        val actingList = arrayListOf<WifiModel>()

        if (scanList.size <= listSize) {
            scanList.forEach {
                actingList.add(
                    WifiModel(
                        scanResult = it,
                        name = it.SSID,
                        power = it.level,
                        description = it.capabilities
                    )
                )
            }
        } else {
            for (i in 0..listSize) {
                actingList.add(
                    WifiModel(
                        scanResult = scanList[i],
                        name = scanList[i].SSID,
                        power = scanList[i].level,
                        description = scanList[i].capabilities
                    )
                )
            }
        }

        resultWifiList.clear()
        resultWifiList.addAll(actingList)
    }
}