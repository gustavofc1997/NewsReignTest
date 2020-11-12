package com.gforeroc.reignapp.di

import com.gforeroc.reignapp.home.HomeActivityModule
import com.gforeroc.reignapp.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class)])
    abstract fun bindHomeActivity(): MainActivity

}
