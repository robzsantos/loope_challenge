package com.br.loopechallenge.movies.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.loopechallenge.LoopeChallengeApplication
import com.br.loopechallenge.R
import com.br.loopechallenge.di.AppComponent
import com.br.loopechallenge.extensions.error
import com.br.loopechallenge.movies.detail.MovieDetailsActivity
import com.br.loopechallenge.uidata.Movie
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.progress_bar_loading.*
import java.io.Serializable
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

    private var adapter: MovieListAdapter? = null

    private var movies: List<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_list)

        appComponent.inject(this)

        setupRecyclerView()

        presenter.attachView(this)

        movies = if (savedInstanceState != null) savedInstanceState.getSerializable(MOVIES) as List<Movie>? else null

        if (movies == null)
            presenter.getMovies()
        else
            showMovies(movies!!)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (movies != null) {
            outState?.putSerializable(MOVIES, movies as Serializable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detachView()
    }

    override fun showMovies(movies: List<Movie>) {
        adapter?.addMovies(movies)

        if (this.movies == null) this.movies = movies
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

    private fun setupRecyclerView() {
        recycler_view_movie_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = MovieListAdapter {
            startActivity(MovieDetailsActivity.newInstance(this, it))
        }

        recycler_view_movie_list.adapter = adapter
    }

    companion object {
        private const val MOVIES = "movies"
    }

}