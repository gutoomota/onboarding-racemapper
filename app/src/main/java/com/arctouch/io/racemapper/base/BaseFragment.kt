package com.arctouch.io.racemapper.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>, C : BaseContract.Component<V,P>> : Fragment(), BaseContract.View {

    protected val component: C by lazy { createComponent() }
    protected val presenter: P by lazy {component.presenter()}

    @StringRes
    protected var containerId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    protected abstract fun createComponent(): C

    fun openFragment(fragment: Fragment){
        val ft = childFragmentManager.beginTransaction()
        containerId?.let { ft.replace(it, fragment) }

        ft.commit()
    }

    fun toast(msg: String){
        context?.let { Toast.makeText(it, msg, Toast.LENGTH_SHORT).show() }
    }

    fun toastLong(msg: String){
        context?.let { Toast.makeText(it, msg, Toast.LENGTH_LONG).show() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}