package com.arctouch.io.racemapper.core

import android.app.Application
import com.arctouch.io.racemapper.di.component.ApplicationComponent
import com.arctouch.io.racemapper.di.component.DaggerApplicationComponent
import com.arctouch.io.racemapper.di.module.ApplicationModule

class RaceMapperApplication: Application() {

    companion object {
        @JvmStatic lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildComponent()
    }

    private fun buildComponent() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
