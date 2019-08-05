package com.br.loopechallenge.di.modules

import com.br.loopechallenge.BuildConfig
import com.br.loopechallenge.di.qualifiers.Default
import com.br.loopechallenge.di.qualifiers.Movies
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Module
class RetrofitModule {

    private fun basicBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    @Movies
    fun providesHomeCatalogRetrofit(@Default client: OkHttpClient, gson: Gson): Retrofit {
        return basicBuilder(gson)
            .baseUrl(BuildConfig.MOVIES_API)
            .client(client)
            .build()
    }

}