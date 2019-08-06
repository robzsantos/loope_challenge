package com.br.loopechallenge.utils.epoxy

import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.airbnb.epoxy.EpoxyModel

/**
 * Created by Robz on 25/03/2018.
 */
abstract class BinderEpoxyModel<T : View> : EpoxyModel<T>() {

    private var unbinder: Unbinder? = null

    private var bindedView: T? = null

    override fun bind(view: T) {
        super.bind(view)

        unbinder = ButterKnife.bind(this, view!!)
    }

    override fun unbind(view: T) {
        super.unbind(view)

        if (view === bindedView) {
            unbinder?.unbind()

            unbinder = null

            bindedView = null
        }
    }
}
