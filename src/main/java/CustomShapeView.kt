package com.CustomShape  // replace with your actual package name

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CustomShapeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#8DEAD7") // light cyan
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(w, h/2f)
            //quadTo(w, h / 2, w * 0.9f, h)
            lineTo(0f, h)
            close()
        }

        canvas.drawPath(path, paint)
    }
}
