package com.example.diceroller

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class options : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        val about : Button = findViewById(R.id.aboutId)
//        var targetNum : EditText = findViewById(R.id.setTargetId)

        about.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.activity_popup)
            dialog.show()
        }
        val newGame : Button = findViewById(R.id.newgameId)
        val dialog = Dialog(this)

        newGame.setOnClickListener {

            dialog.setContentView(R.layout.activity_target_popup)
            dialog.show()

            val play =dialog.findViewById(R.id.PlayId) as Button
            val targetId =dialog.findViewById(R.id.setTargetId) as EditText

            play.setOnClickListener {

                val intent = Intent(this, NewGame::class.java)
                intent.putExtra("myVariableKey", targetId.text.toString())
                startActivity(intent)
                dialog.dismiss()

            }





        }


    }
}