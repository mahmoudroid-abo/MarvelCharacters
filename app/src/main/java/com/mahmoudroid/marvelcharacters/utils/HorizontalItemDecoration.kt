package com.mahmoudroid.marvelcharacters.utils

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration : RecyclerView.ItemDecoration {

    val horizontalSpaceWidth: Float;

    constructor(spacing: Float = 8f) {
        horizontalSpaceWidth = spacing
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            return
        }

        val r = parent.context?.resources
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, horizontalSpaceWidth,
            r?.displayMetrics)

        outRect.left = px.toInt()
    }
}