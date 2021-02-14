package com.example.dragdrop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.dragdrop.R
import com.example.dragdrop.adapter.DragShadow.setupDragShadow
import kotlinx.android.synthetic.main.item_app.view.*

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.Holder>() {
    var data: MutableList<Int> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCallBack: (Int) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return Holder(view)
    }

    private var dropListener: (ViewPosition, Int) -> Unit = { viewPosition, i -> }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    fun setDropListener(listener: (ViewPosition, Int) -> Unit) {
        dropListener = listener
    }


    inner class Holder(val itemVizew: View) : RecyclerView.ViewHolder(itemVizew) {
        //1
        private fun createOnDragListener() = OnDragAndDropListener.Builder()
            .setActionDragEntered {
                it.visibility = View.VISIBLE
            }.setDropListener {
                dropListener(it, 1)
            }.setActionDrop {
            }
            .build()

        init {
            itemView.setOnLongClickListener {
                setupDragShadow(itemVizew)
                onCallBack(1)
                true
            }
        }

        fun bind(position: Int) {
            itemVizew.setOnDragListener(createOnDragListener())
            itemVizew.iappImgIcon.setImageResource(
                when (position) {
                    0 -> R.drawable.um
                    1 -> R.drawable.dois
                    2 -> R.drawable.tres
                    3 -> R.drawable.quatro
                    4 -> R.drawable.cinco
                    5 -> R.drawable.seis
                    6 -> R.drawable.um
                    7 -> R.drawable.sete
                    8 -> R.drawable.oito
                    9 -> R.drawable.nove
                    10 -> R.drawable.dez
                    11 -> R.drawable.onze
                    12 -> R.drawable.doze
                    13 -> R.drawable.treze
                    14 -> R.drawable.quartoze

                    else -> R.mipmap.ic_launcher
                }
            )
        }
    }
}