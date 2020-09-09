package com.arctouch.io.racemapper.di.component

import android.content.Context
import com.arctouch.io.racemapper.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ApplicationModule::class])
interface ApplicationComponent {
    val applicationContext: Context
}