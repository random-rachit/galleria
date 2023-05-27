package com.rachitbhutani.galleria.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.rachitbhutani.galleria.R
import com.rachitbhutani.galleria.databinding.EmptyViewBinding

class EmptyView : ConstraintLayout {

    private lateinit var binding: EmptyViewBinding

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        binding = EmptyViewBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setData(data: Data, onClick: (() -> Unit)?) {
        binding.run {
            ivEmptyView.setImageDrawable(ContextCompat.getDrawable(context, data.drawableRes))
            tvEmptyView.text = data.desc
            btnEmptyView.isVisible = data.buttonText != null
            btnEmptyView.text = data.buttonText
            btnEmptyView.setOnClickListener {
                onClick?.invoke()
            }
        }
    }

    data class Data(
        @DrawableRes val drawableRes: Int,
        val desc: String,
        val buttonText: String?
    )
}