package com.br.loopechallenge.webservice

import com.br.loopechallenge.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Robson on 2019-08-05.
 */
interface MovieListApi {

    @GET("assets.json?alt=media&token=" + BuildConfig.MOVIES_TOKEN)
    fun getList(): Single<MoviesResponse>

}