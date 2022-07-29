package com.jayto.jakmall.utils.component

import android.app.Dialog
import android.content.Context
import com.jayto.jakmall.R

class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
    init {
        window?.decorView?.rootView?.setBackgroundResource(R.color.dialogBackground)
        window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
            insets.consumeSystemWindowInsets()
        }
    }
}