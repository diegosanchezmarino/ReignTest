package com.dsm.reigntestapp.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator: RecyclerView.ItemDecoration() {

     val spacing: Int = 20


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = spacing
        outRect.left = spacing
        outRect.right = spacing

        if(parent.getChildLayoutPosition(view) == 0)
            outRect.top = spacing
        else
            outRect.top = 0

    }

}