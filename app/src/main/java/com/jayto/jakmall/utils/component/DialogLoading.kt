package com.jayto.jakmall.utils.component

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.jayto.jakmall.R
import com.jayto.jakmall.databinding.LayoutLoadingBinding

class DialogLoading(context: Context) {

    private var dialog: CustomDialog
    private val binding: LayoutLoadingBinding by lazy {
        LayoutLoadingBinding.inflate(LayoutInflater.from(context))
    }

    fun start(title: String = "") {
        binding.tvTitle.text = title
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun stop() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    init {
        binding.cvBox.setCardBackgroundColor(Color.parseColor("#70000000"))

        setColorFilter(
            binding.progressBar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        )

        binding.tvTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.setContentView(binding.root)
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}