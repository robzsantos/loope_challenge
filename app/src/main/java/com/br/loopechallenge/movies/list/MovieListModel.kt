package com.br.loopechallenge.movies.list

import androidx.constraintlayout.widget.ConstraintLayout
import com.br.loopechallenge.R
import com.br.loopechallenge.uidata.Movie
import com.br.loopechallenge.utils.epoxy.BinderEpoxyModel
import com.br.loopechallenge.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_movie.view.*


/**
 * Created by Robson on 2019-08-05
 */
class MovieListModel(private val movie: Movie, private val listener: (movie: Movie) -> Unit) :
    BinderEpoxyModel<ConstraintLayout>() {

    override fun getDefaultLayout() = R.layout.item_movie

    override fun bind(view: ConstraintLayout) {
        super.bind(view)

        GlideApp.with(view.context)
            .load(movie.image ?: "")
            .placeholder(R.drawable.image_placeholder)
            .into(view.image_view_movie_image)

        view.text_view_movie_name.text = movie.name ?: ""

        view.setOnClickListener { listener(movie) }

    }
}