package com.example.dragdrop.adapter

import android.content.ClipData
import android.os.Build
import android.view.View

object DragShadow {
    @Suppress("DEPRECATION")

    fun setupDragShadow(view: View) {
        val data = ClipData.newPlainText("simple_text", "text")

        val sb = View.DragShadowBuilder(view)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data, sb, view, 0)
        } else
            view.startDrag(data, sb, view, 0)

     }
}