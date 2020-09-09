package com.arctouch.io.racemapper.di.component

import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.di.scope.ActivityScope
import com.arctouch.io.racemapper.scenes.main.MainPresenter
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface MainComponent : BaseContract.Component<BaseContract.View, MainPresenter>{
    override fun presenter(): MainPresenter
}
