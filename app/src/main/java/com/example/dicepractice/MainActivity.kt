package com.example.dicepractice

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
    //private var correctAns = false       //will be used
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//the actual coding
        //initialize points to 0
        binding.tvP1Score.text = player1Points.toString()
        binding.tvP2Score.text = player2Points.toString()

       // while (player1Points != 5 || player2Points != 5) {//loop this until
            //  correctAns = false

            //set button event handler. below is exclusive of top value.
            binding.btnRollDie.setOnClickListener {
                var diceValue = Random.nextInt(1, 7)  //use kotlin version of random. (Alt + Enter)
                //clever way to avoid many if statements
                var imageName =
                    "@drawable/dice" + diceValue //only works bc png share dice name + value.
                //retrieve resource ID. system knows this.
                var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())

                binding.ivDie.setImageResource(resourceID)

                var num1 = Random.nextInt(0, 100)
                var num2 = Random.nextInt(0, 100)


                var points = diceValue //re-initialized to dice value
                binding.tvAnswerEcho.text = "Click Guess, Check your Answer!"
                //if statements to change turn, or multiply points
                if (diceValue == 4) {
                    binding.tvAnswerEcho.text = "4 rolled, POINTS DOUBLED!!!\nROLL AGAIN!"
                    points *= 2
                    diceValue = Random.nextInt(1, 4)
                }
                //end of if condition
                /*  else if(diceValue ==5){
                    //player loses turn

                }*/
                else if (diceValue == 6) {
                    binding.tvAnswerEcho.text = "4 rolled, POINTS *5, ROLL AGAIN!!!"
                    points *= 5
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

                        answer = num1 - num2
                        val builder = StringBuilder()
                        builder.append(num1)
                        builder.append(" - ")
                        builder.append(num2)
                        builder.append(" = ")
                        binding.tvMath.text = builder.toString()
                    }
                    3 -> {    //multiplication
                        answer = num1 * num2
                        val builder = StringBuilder()
                        builder.append(num1)
                        builder.append(" * ")
                        builder.append(num2)
                        builder.append(" = ")
                        binding.tvMath.text = builder.toString()
                    }
                }//end of when function
                binding.btnGuess.setOnClickListener {
                    var attempt: Int = binding.etEnterAnswer.text.toString()
                        .toInt()   //this is the weirdest conversion.
                    if (attempt == answer) {

                        binding.tvAnswerEcho.text = "CORRECT ANSWER! \nPOINTS EARNED"
                        // correctAns = true
                        if (p1Turn == true) {
                            player1Points += points
                        } else {
                            player2Points += points
                        }
                    } else {
                        binding.tvAnswerEcho.text = "INCORRECT, NO POINTS"
                    }
                }//end of btnGuess

            }//end. test on macbook with virtual AVD

     //   }//while loop i think

        if(player1Points ==5)
        {
            binding.tvAnswerEcho.text = "PLAYER ONE WINS."
        }
        if(player2Points ==5)
        {
            binding.tvAnswerEcho.text = "PLAYER 2 WINS."
        }
    }//on Create end




    //need a screen that says certain player has one.
    private fun getTurn(): Int {
        p1Turn = !p1Turn
        if (p1Turn) {
            binding.tvTurnEcho.text = "Player 1's Turn"
        } else {
            binding.tvTurnEcho.text = "Player 2's Turn"
        }
        return p1Turn.toString().toInt() //hopefully this works.
    }
}



//step one, set viewbinding to true in build.gradle
//step two, copy paste stuff into drawable.

//My best bet in doing this stuff is to master the Control Flow and Function building for Kotlin