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
    var roundNum = 1
    var userRoundDisplay : TextView ?= null
    var targetScore = 101
    var computerImageList = mutableListOf<Boolean>(false,false,false,false,false)
    var isTie = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_game)
        val userTarget:TextView = findViewById(R.id.targetDisplayId)

        targetScore = Integer.parseInt(intent.getStringExtra("myVariableKey"))
        userTarget.text = targetScore.toString()



        val throwbutton : Button = findViewById(R.id.throwId)
        val scorebutton : Button = findViewById(R.id.scoreId)

        val computerScoreDisplay : TextView = findViewById(R.id.comScoreId)
        val userScoreDisplay : TextView = findViewById(R.id.userScoreId)
        userRoundDisplay = findViewById(R.id.userRoundId)


        val computerDice = mutableListOf<ImageView>(findViewById(R.id.dice1Id),findViewById(R.id.dice2Id),findViewById(R.id.dice3Id),findViewById(R.id.dice4Id),findViewById(R.id.dice5Id))
        val userDiceList = mutableListOf<ImageView>(findViewById(R.id.dice6Id),findViewById(R.id.dice7Id),findViewById(R.id.dice8Id),findViewById(R.id.dice9Id),findViewById(R.id.dice10Id))

        var stopImageList = mutableListOf<Boolean>(false,false,false,false,false)

        scorebutton.isEnabled = false


        throwbutton.setOnClickListener {

            if (throwbutton.text=="Throw"){
                if (isTie==false){
                    throwbutton.setText("Rethrow1")
                    scorebutton.isEnabled = true
                    for (i in 0..4){
                        userDiceList[i].isClickable = true
                    }
                }
                computerList = ranList()

                userRoundDisplay?.text =roundNum.toString()
                userList = ranList()


            }else if (throwbutton.text=="Rethrow1"){
                throwbutton.setText("Rethrow2")
                for (i in 0 until 5){
                    if (stopImageList[i]==false){
                        val ranNum = Random().nextInt(6)+1
                        userList[i]=ranNum

                    }
                }
            }else{
                computerReroll()
                computerReroll()
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
                winLoose()

            }
            if (isTie==true){
                updateScore()
                computerScoreDisplay.setText(computerScore.toString())
                userScoreDisplay.setText(userScore.toString())
                updateRound()
                winLoose()
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
            computerReroll()
            computerReroll()
            for (diceIndex in 0 until 5){
                setImage(computerDice[diceIndex],computerList[diceIndex])
                setImage(userDiceList[diceIndex],userList[diceIndex])

            }
            updateScore()

            computerScoreDisplay.setText(computerScore.toString())
            userScoreDisplay.setText(userScore.toString())
            scorebutton.isEnabled = false
            throwbutton.text = "Throw"
            stopImageList = mutableListOf<Boolean>(false,false,false,false,false)
            for (i in 0 until 5){
                userDiceList[i].setBackgroundColor(Color.TRANSPARENT)

            }
            for (i in 0..4){
                userDiceList[i].isClickable = false
            }
            updateRound()

            if (userScore>=targetScore && userScore>computerScore){
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.activity_win_loose_popup)
                dialog.show()

            }

            else if(computerScore>=targetScore && userScore<computerScore){
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.activity_win_loose_popup)
                val resultText = dialog.findViewById<TextView>(R.id.winLooseId)
                resultText.text = "lost"
                resultText.setTextColor(Color.RED)
                dialog.show()

            }
            winLoose()




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
    fun winLoose(){

        if (userScore >= targetScore || computerScore >= targetScore) {
            if (userScore > computerScore) {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.activity_win_loose_popup)
                dialog.setCancelable(true)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setOnCancelListener {
                    finish()
                }
                dialog.show()
            } else if (userScore < computerScore) {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.activity_win_loose_popup)
                val resultText = dialog.findViewById<TextView>(R.id.winLooseId)
                resultText.text = "lost"
                resultText.setTextColor(Color.RED)
                dialog.setCancelable(true)
                dialog.setCanceledOnTouchOutside(false)
                dialog.setOnCancelListener {
                    finish()
                }
                dialog.show()

            } else {
                isTie = true
            }
        }
    }
    fun computerReroll(){
        val random = Random()
        val randomBoolean = random.nextBoolean()
        println(randomBoolean)
        if (randomBoolean==true){
            for (i in 0 until 5){
                computerImageList[i]=random.nextBoolean()
                println(computerList[i])

            }
            for (i in 0 until 5){
                if (computerImageList[i]==false){
                    val ranNum = random.nextInt(6)+1
                    computerList[i]=ranNum
                    println(computerList[i])

                }

            }
        }

    }

}





