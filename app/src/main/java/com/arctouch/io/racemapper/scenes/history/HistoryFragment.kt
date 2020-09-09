package com.arctouch.io.racemapper.scenes.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.io.racemapper.R
import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.base.BaseFragment
import com.arctouch.io.racemapper.core.RaceMapperApplication
import com.arctouch.io.racemapper.di.component.DaggerHistoryComponent
import com.arctouch.io.racemapper.di.component.HistoryComponent

class HistoryFragment: BaseFragment<HistoryContract.View, HistoryPresenter, HistoryComponent>(), HistoryContract.View {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createComponent(): HistoryComponent {
        return DaggerHistoryComponent.builder()
            .applicationComponent(RaceMapperApplication.component)
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