package com.br.loopechallenge.utils.epoxy

import com.airbnb.epoxy.EpoxyAdapter
import com.airbnb.epoxy.EpoxyModel
import com.annimon.stream.Stream
import java.util.*

/**
 * Created by Robz on 25/03/2018.
 */
open class BaseEpoxyAdapter : EpoxyAdapter() {

    protected fun addHead(vararg modelsToAdd: EpoxyModel<*>) {
        addHead(modelsToAdd.toList())
    }

    protected fun addHead(modelsToAdd: Collection<EpoxyModel<*>>) {
        val models = mutableListOf<EpoxyModel<*>>()

        models.addAll(modelsToAdd)

        models.addAll(this.models)

        this.models.clear()

        this.models.addAll(models)

        notifyItemRangeInserted(0, modelsToAdd.size)
    }

    protected fun addModelsAfter(model: EpoxyModel<*>, vararg modelsToAdd: EpoxyModel<*>) {
        addModelsAfter(model, Arrays.asList(*modelsToAdd))
    }

    protected fun addModelsAfter(model: EpoxyModel<*>, modelsToAdd: Collection<EpoxyModel<*>>) {
        val indexOf = models.indexOf(model)

        if (indexOf >= 0) {
            val models = ArrayList<EpoxyModel<*>>()

            val next = indexOf + 1

            models.addAll(this.models.subList(0, next))

            models.addAll(modelsToAdd)

            if (next < this.models.size) {
                models.addAll(this.models.subList(next, this.models.size))
            }

            this.models.clear()

            this.models.addAll(models)

            notifyItemRangeInserted(next, modelsToAdd.size)
        } else {
            addModels(modelsToAdd)
        }
    }

    protected fun removeAllModels(modelsToRemove: Collection<EpoxyModel<*>>) {
        if (!modelsToRemove.isEmpty()) {
            Stream.of(modelsToRemove).forEach { model -> removeModel(model) }
        }
    }

    override fun getModelPosition(model: EpoxyModel<*>?): Int {
        return models.indexOf(model)
    }
}