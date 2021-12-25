package com.example.shaditestapp.utils.dialogutil

import android.content.Context
import com.example.shaditestapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtil {
    fun showTechnicalDialog(context: Context) {
        MaterialAlertDialogBuilder(context, R.style.AlertDialogMaterialTheme)
            .setTitle(R.string.technical_dialog_title)
            .setMessage(R.string.technical_dialog_message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}