package com.arctouch.io.racemapper.scenes.history

import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.base.map.MapView

interface HistoryContract {

    interface View : BaseContract.View

    interface Presenter<V : View>: BaseContract.Presenter<V>

    interface Component<V : View, P: Presenter<V>>: BaseContract.Component<V,P>
}