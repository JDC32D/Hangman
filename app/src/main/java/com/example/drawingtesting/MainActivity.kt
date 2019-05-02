package com.example.drawingtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.game_view_frag.*
import kotlinx.android.synthetic.main.game_view_frag.view.*
import java.util.*
import android.content.Intent



/*
TO-DO:
    +Display completed phrase if player loses
    +Ensure if they hit not to play again that the app closes
    +If they hit back, they are taken back to the main screen
    Hangman needs to be drawn, not a simple image
    Notifications! I almost forgot

If_time_permits
    make it so [enter] inputs whatever is typed on the keyboard
    make the canvas not touchable when user is supposed to guess
 */

class MainActivity : AppCompatActivity(), GameViewFragment.GameListener {

    private var model = GameModel()
    private var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? GameViewFragment
    private var word = ""
    private var display = ""
    private lateinit var guessArray: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        gameFragment?.registerListener(this)
    }

    override fun ready() {
        startGame()
        //Log.wtf("ready", "ready from activity")
    }

    //Set the guessButtons's onclick to be this function
    fun userGuess(click: View) {
        if (click == guessButton) {
            val userInput = playerGuess.text.toString().toLowerCase()
            playerGuess.text = null

            if (userInput.length == 1) {
                if (userInput in word.toLowerCase()) {
                    model.submitGuess(userInput, true)
                    Toast.makeText(applicationContext, "$userInput was in the word",
                        Toast.LENGTH_SHORT).show()
                } else {
                    model.submitGuess(userInput, false)
                    when(drawView.objs.size) {
                        0 -> Toast.makeText(applicationContext, "$userInput not in the word\n Draw head of hangman",
                            Toast.LENGTH_LONG).show()
                        1 -> Toast.makeText(applicationContext, "$userInput not in the word\n Draw torso of hangman",
                            Toast.LENGTH_LONG).show()
                        2,3 -> Toast.makeText(applicationContext, "$userInput not in the word\n Draw arm of hangman",
                            Toast.LENGTH_LONG).show()
                        4,5 -> Toast.makeText(applicationContext, "$userInput not in the word\n Draw leg of hangman",
                            Toast.LENGTH_LONG).show()
                        else -> {
                            Toast.makeText(applicationContext, "Tis but a scratch",
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }

                checkWin()
                updateWord()
                return
            }

            if (userInput.length > 1) {
                Toast.makeText(applicationContext, "Enter one letter at a time",
                    Toast.LENGTH_LONG).show()
                return
            }


        }
    }

    private fun revealWord() {
        playerGuesses.text = word
    }

    override fun onBackPressed() {
        Log.wtf("Back button pressed", "Wtf")
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.chicken)
        builder.setPositiveButton(R.string.restart) {
            _, _ ->
            startActivity(Intent(this, MainActivity::class.java))
        }
        builder.setNegativeButton(R.string.exit) {
            _, _ ->
            finish()
        }
        builder.show()
    }

    private fun gameOverDialog(won: Boolean) {
        revealWord()
        val builder = AlertDialog.Builder(this)
        if(won) {
            builder.setTitle(R.string.win)
        } else {
            builder.setTitle(R.string.lose)
        }
        builder.setMessage(R.string.play)

        builder.setPositiveButton(R.string.yes) {
            _, _ ->
            startGame()
            Toast.makeText(applicationContext,R.string.start,
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(R.string.no) {
            _, _ ->
            finish()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun checkWin(){
        if( model.checkWin(word) ){
            gameOverDialog(true)
        }
    }

    // Sets the playerGuess textView to display
    private fun startGame() {
        getRandWord()
        display = ""
        model.newGame()

        // Not a very Kotlin way of doing a for each
        for ( letter in word ) {
            if (letter.isWhitespace()) {
                display += " "
            } else {
                display += "_"
            }
        }
        playerGuesses.text = display
        showGuesses.text = ""
        drawView.objs.clear()
        Log.wtf("startGame() : display", "$display")
    }

    private fun updateWord() {
        display = "" // Need to do this or it will append the string

        // update hidden words display
        word.forEach {
            if(it.isWhitespace()){
                display += " "
            } else {
                display += model.checkIfGuessed(it.toString())
            }
        }
        playerGuesses.text = display

        // Store guesses and update showGuesses text
        display = ""
        model.incorrectGuesses.forEach {
            display += "$it "
        }
        showGuesses.setText("Incorrect: " + display)

        //Check if player has lost
        if(model.isLoser) {
            gameOverDialog(false)
        }
    }


    private fun getRandWord() {
        guessArray = resources.getStringArray(R.array.words)
        val rand = Random().nextInt(guessArray.size)
        word = guessArray[rand]
        Log.wtf("getRandWord() : word", "$word")
    }

    private fun pauseGuesses() {
        guessButton.isClickable = false
    }

    private fun resumeGuesses() {
        guessButton.isClickable = true
    }

    private fun enableDrawing() {

    }


}
