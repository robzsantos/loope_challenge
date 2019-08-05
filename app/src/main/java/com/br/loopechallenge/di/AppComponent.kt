package com.br.loopechallenge.di

import com.br.loopechallenge.MovieListActivity
import com.br.loopechallenge.di.modules.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Component(modules = [AndroidModule::class, RetrofitModule::class, ApisModule::class, OkHttpClientModule::class, GsonModule::class])
@Singleton
interface AppComponent {

    fun inject(movieListActivity: MovieListActivity)

}