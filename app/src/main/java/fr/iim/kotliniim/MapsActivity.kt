package fr.iim.kotliniim

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fr.iim.kotliniim.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val city: String? = intent.getStringExtra("City")
        Log.d("City : ", city.toString())

        val geocoder = Geocoder(this)
        val position = geocoder.getFromLocationName(city, 1)

        val lng:Double = position[0].longitude
        val lat:Double = position[0].latitude

        val locationCoordinates = LatLng(lat, lng)

        mMap.addMarker(
            MarkerOptions().position(locationCoordinates)
                .icon(
                    BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
                )
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLng(locationCoordinates))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationCoordinates, 15f))
    }
}