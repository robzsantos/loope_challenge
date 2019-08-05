package com.br.loopechallenge

import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Robson on 2019-08-05
 */
class MovieListPresenter @Inject constructor() {

    @Inject
    internal lateinit var interactor: MovieListInteractor

    private var view: MovieListView? = null

    private var disposable: Disposable? = null

    fun attachView(view: MovieListView) {
        this.view = view
    }

    fun detachView() {
        this.view = null

        disposable?.dispose()

        disposable = null
    }
}