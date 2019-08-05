package com.br.loopechallenge.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }
}