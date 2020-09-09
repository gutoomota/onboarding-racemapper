package com.arctouch.io.racemapper.di.component

import com.arctouch.io.racemapper.di.module.MapModule
import com.arctouch.io.racemapper.di.scope.FragmentScope
import com.arctouch.io.racemapper.scenes.run.RunContract
import com.arctouch.io.racemapper.scenes.run.RunPresenter
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [MapModule::class])
interface RunComponent : RunContract.Component<RunContract.View, RunPresenter>{
    override fun presenter(): RunPresenter
}