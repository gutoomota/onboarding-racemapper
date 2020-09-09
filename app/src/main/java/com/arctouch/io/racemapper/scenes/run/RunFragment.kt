package com.arctouch.io.racemapper.scenes.run

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.io.racemapper.R
import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.base.BaseFragment
import com.arctouch.io.racemapper.core.RaceMapperApplication
import com.arctouch.io.racemapper.di.component.DaggerRunComponent
import com.arctouch.io.racemapper.di.component.RunComponent
import com.arctouch.io.racemapper.di.module.MapModule
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_map.*

class RunFragment: BaseFragment<RunContract.View, RunPresenter, RunComponent>(), RunContract.View {

    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_LOCATION = 100

        fun newInstance() = RunFragment()
    }

    var mMap: GoogleMap? = null
    private var mLastLocation: Location? = null
    private var mCurrLocationMarker: Marker? = null
    private var markerOptions: MarkerOptions? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapF) as SupportMapFragment
        mapFragment.getMapAsync(this)

        showLoading()

        //TODO use databinding
        btRun?.setOnClickListener {
            it.isActivated = !it.isActivated
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        markerOptions = MarkerOptions()
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_runner))
            .title(context?.getString(R.string.you))

        assurePermissionToGetLocation()
    }

    override fun onConnected(bundle: Bundle?) {
        if (ContextCompat.checkSelfPermission(presenter.context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(presenter.mGoogleApiClient, presenter.mLocationRequest, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        hideLoading()

        mLastLocation = location
        mCurrLocationMarker?.remove()

        val latLng = LatLng(location.latitude, location.longitude)

        markerOptions?.position(latLng)
        mCurrLocationMarker = mMap?.addMarker(markerOptions)

        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .zoom(17f)
            .build()
        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun assurePermissionToGetLocation(): Boolean {
        return if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_LOCATION)
            false
        } else {
            mMap?.isMyLocationEnabled = true
            presenter.mGoogleApiClient.connect()
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                assurePermissionToGetLocation()
            } else {
                showError(getString(R.string.location_permission))
            }
        }
    }

    override fun createComponent(): RunComponent {
        return DaggerRunComponent.builder()
                .applicationComponent(RaceMapperApplication.component)
                .mapModule(MapModule(this))
                .build()
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun showLoading() {
        when (activity) {
            is BaseContract.View -> (activity as BaseContract.View).showLoading()
        }
    }

    override fun hideLoading() {
        when (activity) {
            is BaseContract.View -> (activity as BaseContract.View).hideLoading()
        }
    }
}
