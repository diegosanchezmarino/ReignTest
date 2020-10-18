package com.dsm.reigntestapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.RED
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dsm.reigntestapp.R

class SwipeToDeleteCallback : SimpleCallback {

    private val adapter: PostsAdapter
    private val icon: Drawable
    private val background: ColorDrawable

    constructor(adapter: PostsAdapter, context: Context) : super(0, LEFT) {
        this.adapter = adapter
        icon = ContextCompat.getDrawable(context, R.drawable.ic_delete)!!
        background = ColorDrawable(RED)
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        if (viewHolder is PostsViewHolder) {
            viewHolder.post?.let { viewHolder.postListInteraction.swiped(it) }
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView: View = viewHolder.itemView
        val backgroundCornerOffset = 20

        val iconMargin = (itemView.height - icon.intrinsicHeight) / 2
        val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom = iconTop + icon.intrinsicHeight

        if (dX < 0) { // Swiping to the left
            var iconLeft = itemView.right - iconMargin - icon.intrinsicWidth;
            var iconRight = itemView.right - iconMargin;
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            background.setBounds(
                itemView.right + dX.toInt() - backgroundCornerOffset,
                itemView.top, itemView.right, itemView.bottom
            )
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c);
        icon.draw(c);
    }
}