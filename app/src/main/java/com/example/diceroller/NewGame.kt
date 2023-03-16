package com.example.diceroller

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*


class NewGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        var computerList = mutableListOf<Int>()
        var userList = mutableListOf<Int>()

        val throwbutton : Button = findViewById(R.id.throwId)
        val scorebutton : Button = findViewById(R.id.scoreId)
        val dice1 : ImageView = findViewById(R.id.dice1Id)
        val dice2 : ImageView = findViewById(R.id.dice2Id)
        val dice3 : ImageView = findViewById(R.id.dice3Id)
        val dice4 : ImageView = findViewById(R.id.dice4Id)
        val dice5 : ImageView = findViewById(R.id.dice5Id)
        val dice6 : ImageView = findViewById(R.id.dice6Id)
        val dice7 : ImageView = findViewById(R.id.dice7Id)
        val dice8 : ImageView = findViewById(R.id.dice8Id)
        val dice9 : ImageView = findViewById(R.id.dice9Id)
        val dice10 : ImageView = findViewById(R.id.dice10Id)

        var computerDice = mutableListOf<ImageView>(dice1,dice2,dice3,dice4,dice5)
        var userDiceList = mutableListOf<ImageView>(dice6,dice7,dice8,dice9,dice10)









        throwbutton.setOnClickListener {
            computerList = ranList()
            userList = ranList()

            if (throwbutton.text=="Throw"){
                throwbutton.setText("Rethrow1")
            }else if (throwbutton.text=="Rethrow1"){
                throwbutton.setText("Rethrow2")
            }else{
                throwbutton.setText("Throw")
            }

//            println(computerList)
//            println(userList)

            for (diceIndex in 0 until 5){
                setImage(computerDice[diceIndex],computerList[diceIndex])
                setImage(userDiceList[diceIndex],userList[diceIndex])
            }

        }
    }

    private fun setImage(any: Any, any1: Any) {

    }

    private fun setImage(image: ImageView, randomValue: Int) {
        if (randomValue==1){
            image.setImageResource(R.drawable.dice1)
        }else if (randomValue==2){
            image.setImageResource(R.drawable.dice2)
        }else if (randomValue==3){
            image.setImageResource(R.drawable.dice3)
        }else if (randomValue==4){
            image.setImageResource(R.drawable.dice4)
        }else if (randomValue==5){
            image.setImageResource(R.drawable.dice5)
        }else if (randomValue==6){
            image.setImageResource(R.drawable.dice6)
        }

    }

    private fun ranList():MutableList<Int>{
        var tempNumList = mutableListOf<Int>()
        for (i in 0 until 5){
            var ranNum = Random().nextInt(6)+1
            tempNumList.add(ranNum)
        }
        return tempNumList
    }

}