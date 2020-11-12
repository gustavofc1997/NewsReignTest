package com.gforeroc.reignapp

import android.content.Context
import androidx.multidex.MultiDex
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


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}