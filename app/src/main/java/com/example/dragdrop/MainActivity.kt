package com.example.dragdrop

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.bt_cl
import kotlinx.android.synthetic.main.activity_main.cincoImageView
import kotlinx.android.synthetic.main.activity_main.doisImageView
import kotlinx.android.synthetic.main.activity_main.quatroImageView
import kotlinx.android.synthetic.main.activity_main.seisImageView
import kotlinx.android.synthetic.main.activity_main.tp_cl
import kotlinx.android.synthetic.main.activity_main.tresImageView
import kotlinx.android.synthetic.main.activity_main.umImageView

class MainActivity : AppCompatActivity() {
    var itemUsed = 0

    val listSelected = mutableListOf<Int>(1, 2, 3, 4, 5, 6)
    val listUnselected = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = List(6) {
            Item(false)
        }
        umImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 1
            list[0].filled = true

        }) {

        })
        doisImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 2
            list[1].filled = true

        }) {})


        tresImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 3
            list[2].filled = true

        }) {

        })
        quatroImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 4
            list[3].filled = true

        }) {})

        cincoImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 5
            list[4].filled = true

        }) {

        })
        seisImageView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
            itemUsed = 6
            list[5].filled = true

        }) {})









        tp_cl.setOnDragListener(object : MyOnLDragListener(fun() {
            Log.i("XRL89", "$itemUsed no layout 1")
            listUnselected.add(itemUsed)
            listUnselected.distinct()
                listSelected.remove(itemUsed)
        }) {})
        bt_cl.setOnDragListener(object : MyOnLDragListener(fun() {
            Log.i("XRL89", "$itemUsed no layout 2")
            listSelected.add(itemUsed)
            listSelected.distinct()
            listUnselected.remove(itemUsed)
        }) {})


    }
}

//
//
open class MyOnLongClickListener(val uni: () -> Unit) : View.OnLongClickListener {
    override fun onLongClick(v: View): Boolean {
        val data = ClipData.newPlainText("simple_text", "text")
        val sb = View.DragShadowBuilder(v)



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(data, sb, v, 0)
        } else
            v.startDrag(data, sb, v, 0)

        v.visibility = View.INVISIBLE
        uni()
        return true
    }

}

open class MyOnLDragListener(val uni: () -> Unit) : View.OnDragListener {
    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                return event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v?.setBackgroundColor(
                    ContextCompat.getColor(
                        v.context,
                        R.color.design_default_color_error
                    )
                )

            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                v?.setBackgroundColor(ContextCompat.getColor(v.context, R.color.black))

            }
            DragEvent.ACTION_DRAG_EXITED -> {
                v?.setBackgroundColor(
                    ContextCompat.getColor(
                        v.context,
                        R.color.material_on_primary_disabled
                    )
                )


            }
            DragEvent.ACTION_DROP -> {
                val view = event.localState as View
                view.setBackgroundColor(
                    ContextCompat.getColor(
                        view.context,
                        R.color.material_on_primary_disabled
                    )
                )
                val owned = view.parent as ViewGroup
                owned.removeView(view)

                val container = v as LinearLayout
                container.addView(view)
                view.visibility = View.VISIBLE
                uni()
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                v?.setBackgroundColor(
                    ContextCompat.getColor(
                        v.context,
                        R.color.material_on_primary_disabled
                    )
                )


            }

        }
        return true
    }


}