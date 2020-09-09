package com.arctouch.io.racemapper.scenes.run

import android.content.Context
import com.arctouch.io.racemapper.base.BasePresenter
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import javax.inject.Inject

class RunPresenter
@Inject
constructor() : BasePresenter<RunContract.View>(), RunContract.Presenter<RunContract.View> {

    @Inject
    lateinit var mGoogleApiClient: GoogleApiClient
    @Inject
    lateinit var mLocationRequest: LocationRequest
    @Inject
    lateinit var context: Context
}