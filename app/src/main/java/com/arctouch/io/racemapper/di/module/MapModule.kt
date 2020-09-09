package com.arctouch.io.racemapper.di.module

import android.content.Context
import com.arctouch.io.racemapper.base.map.MapView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class MapModule (private val mapView: MapView) {

    @Provides
    @Reusable
    internal fun provideGoogleApiClient(context: Context): GoogleApiClient {
        return GoogleApiClient.Builder(context)
            .addConnectionCallbacks(mapView)
            .addOnConnectionFailedListener(mapView)
            .addApi(LocationServices.API)
            .build()
    }

    @Provides
    @Reusable
    internal fun provideLocationRequest(): LocationRequest {
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        return mLocationRequest
    }
}