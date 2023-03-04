package com.example.dicepractice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dicepractice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //will automatically add import above.

    private var points = 1 //re-initialized to dice value
    private var player1Points = 0
    private var player2Points = 0
    private var answer = 0
    private var p1Turn = true

    //checkers to prevent cheating little kids who don't like math games.
    private var dicePressed = false     //will be used to lock multiple spam entries for points
    private var tooManyPresses = false  //used to stop too many presses of dice. make turn based. one dice press, one entry.
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //necessary to save states and such.
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

//the actual coding stuff

        //initialize points to 0
        binding.tvP1Score.text = player1Points.toString()
        binding.tvP2Score.text = player2Points.toString()

        binding.tvAnswerEcho.text = "Press the Roll Button to Play!!!"
        dicePressed = false //until buttonRollDie is pressed

        var pointSet:Int = getTurn()

            //set button event handler. below is exclusive of top value.
            binding.btnRollDie.setOnClickListener {
                if(tooManyPresses == false) //dice roll button only pressed once.
                {
                var diceValue = Random.nextInt(1, 7)  //use kotlin version of random. (Alt + Enter)
                //clever way to avoid many if statements
                var imageName =
                    "@drawable/dice" + diceValue //only works bc png share dice name + value.
                //retrieve resource ID. system knows this.
                var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())

                binding.ivDie.setImageResource(resourceID)

                var num1 = Random.nextInt(0, 100)
                var num2 = Random.nextInt(0, 100)



                    binding.tvAnswerEcho.text = "Click Guess to Check your Answer!"
                    //if statements to change turn, or multiply points
                    if (diceValue == 4) {
                        binding.tvAnswerEcho.text = "4 rolled, POINTS DOUBLED!! AUTOROLL ENABLED"
                        points = 2
                        diceValue = Random.nextInt(1, 4)
                    }
                    //end of if condition
                    /*  else if(diceValue ==5){
                    //player loses turn

                }*/
                    else if (diceValue == 5) {
                        binding.tvAnswerEcho.text =
                            "Yikes, " + binding.tvTurnEcho.text + "lost turn... going to another turn"
                        getTurn()// changes turn

                    } else if (diceValue == 6) {
                        binding.tvAnswerEcho.text = "JACKPOT CHANCE, POINTS *5, ROLLING AGAIN.."
                        points = 5
                        diceValue = Random.nextInt(1, 4) //set stupid thing to something

                    }
                    //Conditional Switch Statement in Kotlin

                    when (diceValue) {
                        1 -> {    //addition
                            answer = num1 + num2

                            val builder =
                                StringBuilder()       //complicated manner to concatenate values to output
                            builder.append(num1)
                            builder.append(" + ")
                            builder.append(num2)
                            builder.append(" = ")
                            binding.tvMath.text = builder.toString()
                        }
                        2 -> {    //subtraction
                            val builder = StringBuilder()
                            if (num1 > num2) {     //conditions set to only positive answers.
                                answer = num1 - num2

                                builder.append(num1)
                                builder.append(" - ")
                                builder.append(num2)
                                builder.append(" = ")
                                binding.tvMath.text = builder.toString()
                            }

                        }
                        3 -> {    //multiplication
                            num1 = Random.nextInt(1, 21) //setting numbers from 1-20
                            num2 = Random.nextInt(1, 21)
                            answer = num1 * num2
                            val builder = StringBuilder()
                            builder.append(num1)
                            builder.append(" * ")
                            builder.append(num2)
                            builder.append(" = ")
                            binding.tvMath.text = builder.toString()
                        }
                    }//end of when function


                pointSet = getTurn()       //call getTurn so everytime roll is called. also re init pointSet
                }//end of tooManyPresses true condition
                else if (tooManyPresses == true){   //if the dice button pressed one too many times
                    binding.tvAnswerEcho.text = "Hey man, enter something, this is turn based game."
                }
                //lock type booleans to prevent little cheating kids. Technique like semaphores learned in OS with Professor Jinhua Guo
                tooManyPresses = true   //only reset to false once a guess button is pressed. certain player cannot change answer type.
                dicePressed = true      //can now use guess to enter points without cheaters.
            }//end of buttonRoll
        binding.btnGuess.setOnClickListener {
            var attempt: Int = binding.etEnterAnswer.text.toString().toInt()   //this is the weirdest conversion. checks entry

            tooManyPresses = false  //sets back to false so die can be used.
            if (attempt == answer) {    //if right answer is gotten

                binding.tvAnswerEcho.text = "CORRECT ANSWER! \nPOINTS EARNED"
                // correctAns = true
                if(dicePressed == true) {       //only if the dice pressed option has been pushed can i
                    setPoints(points, pointSet)
                }
                else if (dicePressed == false){
                    binding.tvAnswerEcho.text = "Nice try fella, play by the rules and press dice roll."
                }
            } else {
                binding.tvAnswerEcho.text = "INCORRECT, NO POINTS"
            }

            dicePressed = false //reset to false to prevent spam points. hopefully works.

            var win= checkWinner(player1Points, player2Points)   //call function
            when(win) //checks to see every rotation if a player won
            {       //if one of the two players reach 20.
                1-> binding.tvAnswerEcho.text = "PLAYER 1 WINNER!!! press Guess to Play Again!"
                2-> binding.tvAnswerEcho.text = "PLAYER 2 WINNER!!! press Guess to Play Again!"
            }
            //I NEED TO SEE HOW TO STOP OVERUSING CORRECTPOINTS STUFF SO YOU CANT SPAM CORRECT
            //will have to use fragments
        }//end of btnGuess


            }//end.
    private fun checkWinner(p1: Int, p2: Int): Int
    {
        var winner = 0

        if (p1 >= 20) //set to 20 if winner.
        {
            winner = 1
        }
        else if (p2>= 20)
        {
            winner = 2
        }
        else {
            winner = 0
        }

        if(p1== 21 || p2 ==21)  //if player decide to
        {
            ResetGame()
        }
        return winner
    }

    private fun setPoints (pointAmt: Int, player: Int)
    {

        when(player)      //check to see if stuff set
        {
            1-> {
                player1Points += pointAmt
                binding.tvP1Score.text = player1Points.toString()
            }
            0-> {
                player2Points += pointAmt
                binding.tvP2Score.text = player2Points.toString()
            }
        }
    }

    //need a screen that says certain player has one.
    private fun getTurn(): Int {        //use getTurn for Points setting
        p1Turn = !p1Turn
        if (p1Turn) {
            binding.tvTurnEcho.text = "Player 1's Turn"
            return 1
        } else {
            binding.tvTurnEcho.text = "Player 2's Turn"
            return 0
        }

    }//end of getTurn

    private fun ResetGame() {       //void function.
        player1Points = 0
        player2Points = 0
        binding.tvP1Score.text = player1Points.toString()
        binding.tvP2Score.text = player2Points.toString()
        getTurn()
    }



    }//on Create end




//check end game function.

//turn base
//points -> ID on scoreboard.

//step one, set viewbinding to true in build.gradle
//step two, copy paste stuff into drawable.

//My best bet in doing this stuff is to master the Control Flow and Function building for Kotlin