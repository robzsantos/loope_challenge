package com.br.loopechallenge.di.modules

import android.content.Context
import com.br.loopechallenge.di.interceptors.LoggerInterceptor
import com.br.loopechallenge.di.qualifiers.Default
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Module
class OkHttpClientModule {

    @Provides
    @Singleton
    @Default
    fun providesDefaultOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.cache(Cache(File(context.cacheDir, CACHE_FOLDER), CACHE_MAX_SIZE.toLong()))

        builder.addNetworkInterceptor(LoggerInterceptor())

        return builder.build()
    }

    companion object {

        private const val CACHE_FOLDER = "http_cache"

        private const val CACHE_MAX_SIZE = 1024 * 1024 * 10 //10MB
    }
}