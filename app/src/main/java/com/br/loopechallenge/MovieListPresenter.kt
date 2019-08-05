package com.br.loopechallenge

import com.br.loopechallenge.uidata.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    fun getMovies() {
        view?.startLoading()

        disposable = interactor.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->

                val movies = it.map {
                    Movie.from(it)
                }

                view?.showMovies(movies)

                view?.endLoading()

            }) { throwable ->

                view?.endLoading()

                val message: String? = throwable.message

                view?.showError(message)
            }
    }
}