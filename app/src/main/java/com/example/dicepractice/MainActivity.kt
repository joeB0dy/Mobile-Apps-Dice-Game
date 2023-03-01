package com.example.dicepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dicepractice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //will automatically add import above.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var diceValue = Random.nextInt(1,7)  //use kotlin version of random. (Alt + Enter)
        //set button event handler. below is exclusive of top value.
        binding.btnRollDie.setOnClickListener {

            //clever way to avoid many if statements
            var imageName = "@drawable/dice" + diceValue //only works bc png share dice name + value.
            //retrieve resource ID. system knows this.
            var resourceID = resources.getIdentifier(imageName, "drawable", getPackageName())

            binding.ivDie.setImageResource(resourceID)
        }//end. test on macbook with virtual AVD




        //guess button stuff
        binding.btnGuess.setOnClickListener{

        }



    }//on Create end
}
//step one, set viewbinding to true in build.gradle
//step two, copy paste stuff into drawable.

//My best bet in doing this stuff is to master the Control Flow and Function building for Kotlin