package com.arctouch.io.racemapper.base

interface BaseContract {

    interface View {
        fun showError(error: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter<V : View> {
        fun getView(): V?
        fun attachView(view: V)
        fun detachView()
    }

    interface Component<V : View, P: Presenter<V>> {
        fun presenter(): P
    }
}