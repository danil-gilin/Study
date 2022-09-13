package com.example.premission.presenter.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.premission.R
import com.example.premission.databinding.FragmentMapsBinding
import com.example.premission.entity.place.Feature

import com.example.premission.entity.place.Places
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MapsFragment : Fragment() {

    @Inject
   lateinit var mapViewModelFactory:MapViewModelFactory

    private val viewModel :MapViewModel by viewModels {mapViewModelFactory  }


    private lateinit var map: GoogleMap
    private  var locationListener: LocationSource.OnLocationChangedListener?=null
    private lateinit var fusedClient: FusedLocationProviderClient
    private var needAnimateUpdate=false
    private var needMoveCamera=true
    private val launcer=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        map->
        if(map.values.isNotEmpty() && map.values.all { it }){
            startLocation()
        }
    }

    private val handler=android.os.Handler(Looper.getMainLooper())
    private val cameraMoveRunnable= Runnable {
        needMoveCamera=true
    }


    private val locationCallback=object :LocationCallback(){
        override fun onLocationResult(rezult: LocationResult) {
            rezult.lastLocation?.let { location ->
            locationListener?.onLocationChanged(location)
               val animateCamera = CameraUpdateFactory.
                newLatLngZoom(LatLng(location.latitude,location.longitude),13f)
                if(needMoveCamera){
                if(needAnimateUpdate){
                    map?.animateCamera(animateCamera)
                }else{
                    needAnimateUpdate=true
                    map?.moveCamera(animateCamera)
                }}

                viewModel.getPlace(location.longitude-1,
                    location.latitude-1,
                    location.longitude+1,
                    location.latitude+1)
           }

        }
    }

    lateinit var binding:FragmentMapsBinding


    private val callback = OnMapReadyCallback { googleMap ->
        map=googleMap
        checkPermission()
        with(googleMap.uiSettings){
            isZoomControlsEnabled=true
            isMyLocationButtonEnabled=true
        }

      googleMap.setLocationSource(object :LocationSource{
          override fun activate(p0: LocationSource.OnLocationChangedListener) {
            locationListener=p0
          }

          override fun deactivate() {
              locationListener=null
          }
      })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMapsBinding.inflate(inflater)

       fusedClient= LocationServices.getFusedLocationProviderClient(requireContext())
        binding.mapOverlay.setOnTouchListener { view, motionEvent ->
          handler.removeCallbacks (cameraMoveRunnable)
            needMoveCamera=false
            handler.postDelayed(cameraMoveRunnable,7000)
            false
        }

        viewModel.place.onEach {
            addMarkerOnMap(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)


        return binding.root

    }

    private fun addMarkerOnMap(places: List<Feature>) {
        places.forEach {
            val positionItem= LatLng(it.geometry.coordinates[1],it.geometry.coordinates[0])
            map.addMarker(MarkerOptions()
                .position(positionItem)
                .title(it.properties.name)
                .snippet(it.properties.kinds)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        fusedClient.removeLocationUpdates(locationCallback)
        needAnimateUpdate=false
    }

    private fun startLocation() {
        map.isMyLocationEnabled=true
        val request=LocationRequest.create()
            .setInterval(1000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)

        fusedClient.requestLocationUpdates(
            request,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun checkPermission(){
        if(REQUIRED_PERMISSONS.all { permission->
                ContextCompat.checkSelfPermission(requireContext(),permission)==PackageManager.PERMISSION_GRANTED
            }){
            startLocation()
        }else{
            launcer.launch(REQUIRED_PERMISSONS)
        }
    }

    companion object{
        private val REQUIRED_PERMISSONS: Array<String> = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}