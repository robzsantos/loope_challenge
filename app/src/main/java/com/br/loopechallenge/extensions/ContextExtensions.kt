package com.br.loopechallenge.extensions

import android.app.AlertDialog
import android.content.Context
import com.br.loopechallenge.R

/**
 * Created by Robson on 2019-07-27.
 */

fun Context.error(message: String?, listener: (() -> Unit)? = null) {
    info(
        message ?: getString(R.string.message_unknown_error),
        listener
    )
}

fun Context.info(message: String?, listener: (() -> Unit)? = null) {
    if (message?.isNotBlank() == true) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.dialog_ok) { dialog, which ->
                dialog.dismiss()
                listener?.invoke()
            }
            .setOnCancelListener { dialog ->
                dialog.dismiss()
                listener?.invoke()
            }
            .show()
    }
}