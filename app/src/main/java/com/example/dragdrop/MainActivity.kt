package com.example.dragdrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dragdrop.adapter.AvailableAdapter
import com.example.dragdrop.adapter.SelectedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecAvailable.adapter = AvailableAdapter().apply {
            data = List(6) { it }
        }

        mainSeparator.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        mainRecSelected.adapter = SelectedAdapter()
    }
}