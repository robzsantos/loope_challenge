package com.br.loopechallenge.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Module
class GsonModule {

    @Provides
    @Singleton
    fun providesGson(context: Context): Gson = GsonBuilder().create()
}