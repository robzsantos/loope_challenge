package com.br.loopechallenge

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.br.loopechallenge.di.AppComponent
import com.br.loopechallenge.extensions.error
import com.br.loopechallenge.uidata.Movie
import kotlinx.android.synthetic.main.progress_bar_loading.*
import javax.inject.Inject

/**
 * Created by Robson on 2019-08-05
 */
class MovieListActivity : AppCompatActivity(), MovieListView {

    @Inject
    lateinit var presenter: MovieListPresenter

    private val appComponent: AppComponent
        get() {
            val application = application as LoopeChallengeApplication

            return application.appComponent ?: throw IllegalStateException()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        appComponent.inject(this)

        presenter.attachView(this)

        presenter.getMovies()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun showMovies(movies: List<Movie>) {

    }

    override fun showError(message: String?) {
        error(message)
    }

    override fun startLoading() {
        constraintlayout_loading_frame.visibility = View.VISIBLE
    }

    override fun endLoading() {
        constraintlayout_loading_frame.visibility = View.GONE
    }

}
