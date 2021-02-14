package com.example.dragdrop

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dragdrop.adapter.AvailableAdapter
import com.example.dragdrop.adapter.SelectedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var callback = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val availableAdapter = AvailableAdapter().apply {
            data = MutableList(6) { it }
        }
        val selectedAdapter = SelectedAdapter().apply {
            data = MutableList(6) { it + 7 }
        }

        selectedAdapter.onCallBack = {
            callback = it
        }
        availableAdapter.onCallBack = {
            callback = it
        }
        mainRecAvailable.adapter = availableAdapter
        availableAdapter.setDropListener { viewPosition, i ->
            if (callback ==i) {
                val position = mainRecSelected.getChildAdapterPosition(viewPosition.view)
                val positionTwo = mainRecSelected.getChildAdapterPosition(viewPosition.viewTwo)
                val itemChanged = availableAdapter.data[positionTwo]
                val itemGet = availableAdapter.data[position]
                availableAdapter.data[position] = itemChanged
                availableAdapter.data[positionTwo] = itemGet
                availableAdapter.notifyDataSetChanged()

            } else {
                val position = mainRecAvailable.getChildAdapterPosition(viewPosition.view)
                val positionTwo = mainRecAvailable.getChildAdapterPosition(viewPosition.viewTwo)
                val itemChanged = availableAdapter.data[position]
                val itemGet = selectedAdapter.data[positionTwo]
                availableAdapter.data[position] = itemGet
                selectedAdapter.data[positionTwo] = itemChanged
                availableAdapter.notifyDataSetChanged()
                selectedAdapter.notifyDataSetChanged()
            }
         }

        mainSeparator.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        mainRecSelected.adapter = selectedAdapter
        selectedAdapter.notifyDataSetChanged()

        selectedAdapter.setDropListener { viewPosition, i ->
            if (callback == i) {
                val position = mainRecSelected.getChildAdapterPosition(viewPosition.view)
                val positionTwo = mainRecSelected.getChildAdapterPosition(viewPosition.viewTwo)
                val itemChanged = selectedAdapter.data[positionTwo]
                val itemGet = selectedAdapter.data[position]
                selectedAdapter.data[position] = itemChanged
                selectedAdapter.data[positionTwo] = itemGet
                selectedAdapter.notifyDataSetChanged()

            } else {
                val position = mainRecAvailable.getChildAdapterPosition(viewPosition.view)
                val positionTwo = mainRecAvailable.getChildAdapterPosition(viewPosition.viewTwo)
                val itemChanged = selectedAdapter.data[position]
                val itemGet = availableAdapter.data[positionTwo]
                selectedAdapter.data[position] = itemGet
                availableAdapter.data[positionTwo] = itemChanged
                selectedAdapter.notifyDataSetChanged()
                availableAdapter.notifyDataSetChanged()

            }
        }
    }
}


