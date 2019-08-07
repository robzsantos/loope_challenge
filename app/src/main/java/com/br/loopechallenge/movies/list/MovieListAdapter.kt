package com.br.loopechallenge.movies.list

import com.br.loopechallenge.utils.epoxy.BaseEpoxyAdapter
import com.br.loopechallenge.uidata.Movie

/**
 * Created by Robson on 2019-08-05
 */
class MovieListAdapter(private val listener: (movie: Movie) -> Unit) : BaseEpoxyAdapter() {

    fun addMovies(movies: List<Movie>) {

        addModels(
            movies.map { MovieListModel(it, listener) }.toList()
        )

    }

}