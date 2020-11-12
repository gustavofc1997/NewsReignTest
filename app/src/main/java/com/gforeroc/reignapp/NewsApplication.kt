package com.gforeroc.reignapp

import com.gforeroc.reignapp.di.AppComponent
import com.gforeroc.reignapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsApplication : DaggerApplication() {

    private var mAppComponent: AppComponent? = null
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .application(this)
            .build()
        mAppComponent = component
        component.inject(this)
        return component
    }
}