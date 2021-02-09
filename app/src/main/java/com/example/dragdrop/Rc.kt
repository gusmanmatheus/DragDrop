package com.example.dragdrop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.itemRC

class Rc : RecyclerView.Adapter<Rc.Holder>() {
    var data = mutableListOf<Int>()
    var onItemClick: ((Int) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
                itemView.setOnLongClickListener(object : MyOnLongClickListener(fun() {
                   
                        data.getOrNull(adapterPosition)?.let {
                            onItemClick(it)
                        }}
                    )
                {})
        }
        fun bind(num: Int) {
            val image =
                when (num) {
                    1 -> R.drawable.um
                    2 -> R.drawable.dois
                    3 -> R.drawable.tres
                    4 -> R.drawable.quatro
                    5 -> R.drawable.cinco
                    6 -> R.drawable.seis
                    else -> R.mipmap.ic_launcher
                }
            itemView.itemRC.setImageResource(image)
        }
    }
}