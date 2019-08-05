package com.br.loopechallenge

/**
 * Created by Robson on 2019-08-05
 */
interface MovieListView {

    fun showError(message: String?)

    fun startLoading()

    fun endLoading()

}