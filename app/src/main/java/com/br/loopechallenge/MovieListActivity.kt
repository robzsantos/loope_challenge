package com.br.loopechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.loopechallenge.di.AppComponent
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
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun showError(message: String?) {

    }

    override fun startLoading() {

    }

    override fun endLoading() {

    }

}
