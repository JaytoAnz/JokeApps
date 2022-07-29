package com.jayto.jakmall.utils.component

import android.content.Context
import android.view.LayoutInflater
import com.jayto.jakmall.databinding.LayoutDetailsBinding

class DialogDetails(context: Context) {

    private var dialog: CustomDialog
    private val binding: LayoutDetailsBinding by lazy {
        LayoutDetailsBinding.inflate(LayoutInflater.from(context))
    }

    init {
        dialog = CustomDialog(context)

        binding.btnOk.setOnClickListener {
            if (dialog.isShowing) dialog.dismiss()
        }
        dialog.setContentView(binding.root)
    }

    private fun setDescription(desc: String) {
        binding.tvTitle.text = desc
    }

    fun show(desc: String) {
        setDescription(desc)
        if (!dialog.isShowing) {
            dialog.show()
        }
    }
}