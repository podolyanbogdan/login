package com.example.login.ui.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.login.repository.MyRepository
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MarkerCreator(private val context: Context) {

    //что бы миллион маркеров с ресайклера не стакались друг на друга
    var fakeLatLng = 0.0001550
    fun placeMarkerOnMap(currentLatLong: LatLng, myMap: GoogleMap) {
        myMap.clear()
        MyRepository(context).getMarkersQuantity().forEach { marker ->
            val fakeLocation = LatLng(
                marker.location.latitude + fakeLatLng,
                marker.location.longitude + fakeLatLng
            )
            val markerOptions = MarkerOptions().position(fakeLocation)
            markerOptions.title("${marker.character} level ${marker.level}")
            markerOptions.snippet("${currentLatLong.longitude} - ${currentLatLong.latitude}")
            markerOptions.icon(bitmapDescriptorFromVector(marker.characterImage))
            myMap.addMarker(markerOptions)
            fakeLatLng += fakeLatLng
        }
    }


    private fun bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
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

}