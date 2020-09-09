package com.arctouch.io.racemapper.di.component

import com.arctouch.io.racemapper.di.scope.FragmentScope
import com.arctouch.io.racemapper.scenes.history.HistoryContract
import com.arctouch.io.racemapper.scenes.history.HistoryPresenter
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface HistoryComponent : HistoryContract.Component<HistoryContract.View, HistoryPresenter>{
    override fun presenter(): HistoryPresenter
}