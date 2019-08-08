package com.br.loopechallenge.di.modules

import com.br.loopechallenge.di.qualifiers.Movies
import com.br.loopechallenge.webservice.MovieListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Robson on 2019-08-05.
 */
@Module
class ApisModule {

    @Provides
    @Singleton
    fun providesHomeCatalogApi(@Movies retrofit: Retrofit): MovieListApi = retrofit.create(MovieListApi::class.java)

}