package com.example.dragdrop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.dragdrop.R
import com.example.dragdrop.adapter.DragShadow.setupDragShadow
import kotlinx.android.synthetic.main.item_app.view.*

class AvailableAdapter : RecyclerView.Adapter<AvailableAdapter.Holder>() {

    var data: List<Int> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var dropListener: (ViewPosition) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    fun setDropListener(listener: (ViewPosition) -> Unit) {
        dropListener = listener
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun createOnDragListener() = OnDragAndDropListener.Builder()
                .setActionDragEntered {
                    it.visibility = View.INVISIBLE
                }
                .setActionDragEnded {
                    it.isVisible = true
                    it.setOnDragListener { _, _ -> true }
                }.setDropListener {
                    dropListener(it)
                }.build()

        init {
            itemView.setOnLongClickListener {
                itemView.setOnDragListener(createOnDragListener())

                setupDragShadow(itemView)

                true
            }
        }

        fun bind(position: Int) {
            itemView.iappImgIcon.setImageResource(
                    when (position) {
                        0 -> R.drawable.um
                        1 -> R.drawable.dois
                        2 -> R.drawable.tres
                        3 -> R.drawable.quatro
                        4 -> R.drawable.cinco
                        5 -> R.drawable.seis
                        else -> R.mipmap.ic_launcher
                    }
            )
        }
    }

    companion object {
        val TAG = "Stone"
    }
}