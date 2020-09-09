package com.arctouch.io.racemapper.scenes.main

import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.base.BasePresenter
import javax.inject.Inject

class MainPresenter
@Inject
constructor() : BasePresenter<BaseContract.View>(), BaseContract.Presenter<BaseContract.View>