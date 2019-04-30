package com.example.drawingtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.game_view_frag.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var model = GameModel()
    private var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
    private var word = ""
    private var display = ""
    //private var viewListener: DrawView? = null
    private lateinit var guessArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val vModel = ViewModelProviders.of(this).get(HangmanViewModel::class.java)
//        vModel.getWordsToGuess()


    }

    //It says view is not used but it LIES and breaks if I take it out
    fun startClicked(view: View) {
        if (gameFragment == null) {
            gameFragment = GameViewFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, gameFragment!!)
                .commit()
        }
        findViewById<Button>(R.id.startBtn).isEnabled = false

    }

    fun userGuess(click: View) {
        if (click == guessButton) {
            val userInput = playerGuess.text.toString().toLowerCase()
            playerGuess.text = null

            // Change to for each loop for more than one character?
            // That could cause user to lose all at once, so maybe not
            if (userInput.length == 1) {
                if (userInput in word.toLowerCase()) {
                    model.submitGuess(userInput, true)
                    Toast.makeText(applicationContext, "$userInput was in the word",
                        Toast.LENGTH_SHORT).show()
                } else {
                    model.submitGuess(userInput, false)
                    Toast.makeText(applicationContext, "$userInput was not in the word",
                        Toast.LENGTH_SHORT).show()
                }
                //updateWordString()
                return
            }

            if (userInput.length > 1) {
                Toast.makeText(applicationContext, "Enter one letter at a time",
                    Toast.LENGTH_LONG).show()
                return
            }

            //Have User Draw hangman

        }
    }

    private fun startGame() {
        getRandWord()
        display = ""
        model.newGame()

        for ( letter in word ) {
            if (letter.equals(" ")) {
                display += " "
            } else {
                display += "_"
            }
        }
        playerGuesses.text = display
    }


    private fun getRandWord() {
        guessArray = resources.getStringArray(R.array.words)
        val rand = Random().nextInt(guessArray.size)
        word = guessArray[rand]
    }

//    override fun viewLoaded() {
//        Log.e("Activity", "Activity is listening to viewLoaded()")
//    }
//
//    override fun drawComplete() {
//        Log.e("Activity", "Activity is listening to drawComplete()")
//    }

}
