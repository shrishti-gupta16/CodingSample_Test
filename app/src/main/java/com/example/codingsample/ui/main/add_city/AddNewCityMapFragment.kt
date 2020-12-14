package com.example.codingsample.ui.main.add_city

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.codingsample.R
import com.example.codingsample.data.LocationDetail
import com.example.codingsample.databinding.FragmentMapAddNewCityBinding
import com.example.codingsample.ui.main.LocationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*


class AddNewCityMapFragment() : Fragment(), OnMapReadyCallback {
    private lateinit var mViewBinding : FragmentMapAddNewCityBinding
    private lateinit var mMap : GoogleMap

    private val mViewModel by viewModels<LocationViewModel>()
    var latLng : LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = FragmentMapAddNewCityBinding.inflate(inflater, container, false)
        mViewBinding.lifecycleOwner = viewLifecycleOwner
        initUI()
        setButtonListeners()
        return mViewBinding.root
    }

    private fun initUI() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    private fun setButtonListeners() {
        mViewBinding.btnSave.setOnClickListener {
            latLng?.let {

                val geocoder = Geocoder(context, Locale.getDefault())
                val address =
                    geocoder.getFromLocation(it.latitude, it.longitude, 1)[0]

                val latitude = it.latitude
                val longitude = it.longitude
                val cityName = address.locality
                val currentAddress = address.getAddressLine(0)
                val isDefault = if (mViewBinding.cbDefault.isChecked) 1 else 0

                val locationDetail =
                    LocationDetail(latitude, longitude, cityName, currentAddress, isDefault)
                mViewModel.addLocation(locationDetail)

                findNavController().popBackStack()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap

            mMap.apply {
                uiSettings.isZoomControlsEnabled = true

                setOnMarkerDragListener(object : OnMarkerDragListener {
                    override fun onMarkerDragStart(marker: Marker) {}
                    override fun onMarkerDrag(marker: Marker) {}
                    override fun onMarkerDragEnd(marker: Marker) {
                        latLng = marker.position
                    }
                })

                // Add a marker in Sydney and move the camera
                val defaultLocation = LatLng(26.7949218, 75.8225235)
                addMarker(
                    MarkerOptions().position(defaultLocation).draggable(true).title("Marker in Jaipur")
                )
                moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12.0f))
            }

        }
    }

}