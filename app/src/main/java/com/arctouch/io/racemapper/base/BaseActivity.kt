package com.arctouch.io.racemapper.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>, C : BaseContract.Component<V,P>> : AppCompatActivity(), BaseContract.View {

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
        val ft = supportFragmentManager.beginTransaction()
        containerId?.let { ft.replace(it, fragment) }

        ft.commit()
    }

    fun toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun toastLong(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}