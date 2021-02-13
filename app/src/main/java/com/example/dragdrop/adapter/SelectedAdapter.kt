package com.example.dragdrop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dragdrop.R
import kotlinx.android.synthetic.main.item_app.view.*

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
}