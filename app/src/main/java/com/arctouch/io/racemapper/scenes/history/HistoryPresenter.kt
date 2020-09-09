package com.arctouch.io.racemapper.scenes.history

import com.arctouch.io.racemapper.base.BasePresenter
import javax.inject.Inject

class HistoryPresenter
@Inject
constructor() : BasePresenter<HistoryContract.View>(), HistoryContract.Presenter<HistoryContract.View>