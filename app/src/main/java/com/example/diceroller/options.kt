package com.example.diceroller

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class options : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        val about : Button = findViewById(R.id.aboutId)

        about.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_popup)
            dialog.show()
        }

    }
}