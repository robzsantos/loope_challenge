package com.br.loopechallenge

import com.br.loopechallenge.uidata.Movie


/**
 * Created by Robson on 2019-08-05
 */
interface MovieListView {

    fun showError(message: String?)

    fun startLoading()

    fun endLoading()

    fun showMovies(movies: List<Movie>)

}