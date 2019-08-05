package com.br.loopechallenge

import android.app.Application
import com.br.loopechallenge.di.AppComponent
import com.br.loopechallenge.di.DaggerAppComponent
import com.br.loopechallenge.di.modules.AndroidModule

/**
 * Created by Robson on 2019-08-05.
 */
class LoopeChallengeApplication : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .androidModule(AndroidModule(this))
            .build()

    }
}