package com.juvcarl.batch.mcs.characterlist

import android.app.Application
import com.juvcarl.batch.mcs.characterlist.di.AppComponent

class App: Application() {

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this,getString(R.string.base_url))!!
    }
}