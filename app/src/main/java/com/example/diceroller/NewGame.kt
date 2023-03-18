package com.example.diceroller

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import java.util.*


class NewGame : AppCompatActivity() {
    var computerList = mutableListOf<Int>()
    var userList = mutableListOf<Int>()
    var computerScore = 0
    var userScore = 0
    var roundNum = 0
    var userRoundDisplay : TextView ?= null



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)



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
        val computerScoreDisplay : TextView = findViewById(R.id.comScoreId)
        val userScoreDisplay : TextView = findViewById(R.id.userScoreId)
        userRoundDisplay = findViewById(R.id.userRoundId)


        val computerDice = mutableListOf<ImageView>(dice1,dice2,dice3,dice4,dice5)
        val userDiceList = mutableListOf<ImageView>(dice6,dice7,dice8,dice9,dice10)

        var stopImageList = mutableListOf<Boolean>(false,false,false,false,false)
        scorebutton.isEnabled = false


        throwbutton.setOnClickListener {
            computerList = ranList()


            if (throwbutton.text=="Throw"){
                throwbutton.setText("Rethrow1")
                scorebutton.isEnabled = true
                userList = ranList()
                for (i in 0..4){
                    userDiceList[i].isClickable = true
                }
            }else if (throwbutton.text=="Rethrow1"){
                throwbutton.setText("Rethrow2")
                for (i in 0 until 5){
                    if (stopImageList[i]==false){
                        val ranNum = Random().nextInt(6)+1
                        userList[i]=ranNum

                    }
                }
            }else{
                throwbutton.setText("Throw")
                updateScore()
                computerScoreDisplay.setText(computerScore.toString())
                userScoreDisplay.setText(userScore.toString())
                for (i in 0..4){
                    userDiceList[i].isClickable = false
                }
                for (i in 0 until 5){
                    if (stopImageList[i]==false){
                        val ranNum = Random().nextInt(6)+1
                        userList[i]=ranNum



                    }
                }
                scorebutton.isEnabled = false
                updateRound()

            }
            stopImageList = mutableListOf<Boolean>(false,false,false,false,false)
            for (i in 0 until 5){
                userDiceList[i].setBackgroundColor(Color.TRANSPARENT)

            }


            for (diceIndex in 0 until 5){
                setImage(computerDice[diceIndex],computerList[diceIndex])
                setImage(userDiceList[diceIndex],userList[diceIndex])

            }

        }
        scorebutton.setOnClickListener {
            updateScore()

            computerScoreDisplay.setText(computerScore.toString())
            userScoreDisplay.setText(userScore.toString())
            scorebutton.isEnabled = false
            throwbutton.text = "Throw"
            stopImageList = mutableListOf<Boolean>(false,false,false,false,false)
            for (i in 0 until 5){
                userDiceList[i].setBackgroundColor(Color.TRANSPARENT)

            }
            updateRound()

        }

        for (i in 0..4){
            userDiceList[i].setOnClickListener {
                if (stopImageList[i]==false){
                    stopImageList[i]=true
                    userDiceList[i].setBackgroundColor(R.color.purple_200)
                }else{
                    stopImageList[i]=false
                    userDiceList[i].setBackgroundColor(Color.TRANSPARENT)
                }
            }
            userDiceList[i].isClickable = false


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
        val tempNumList = mutableListOf<Int>()
        for (i in 0 until 5){
            val ranNum = Random().nextInt(6)+1
            tempNumList.add(ranNum)
        }
        return tempNumList
    }
    fun updateScore() {
        for (count in 0..4){
            computerScore += computerList[count]
            userScore += userList[count]
        }

    }
    fun updateRound() {
        roundNum+=1
        userRoundDisplay?.text=roundNum.toString()

        }

    }





