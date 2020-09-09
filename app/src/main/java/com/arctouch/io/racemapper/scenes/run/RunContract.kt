package com.arctouch.io.racemapper.scenes.run

import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.base.map.MapView

interface RunContract {

    interface View : BaseContract.View, MapView {
        fun assurePermissionToGetLocation(): Boolean
    }

    interface Presenter<V : View>: BaseContract.Presenter<V>

    interface Component<V : View, P: Presenter<V>>: BaseContract.Component<V,P>
}