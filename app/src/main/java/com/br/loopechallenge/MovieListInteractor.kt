package com.br.loopechallenge

import com.br.loopechallenge.webservice.MovieInfosResponse
import com.br.loopechallenge.webservice.MovieListApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Robson on 2019-08-05
 */
class MovieListInteractor @Inject constructor() {

    @Inject
    lateinit var api: MovieListApi

    fun getMovies(): Single<List<MovieInfosResponse>> = api.getList().map { list -> list.movies }

}