package com.example.drawingtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.game_view_frag.*
import java.util.*
import android.content.Intent


/*
TO-DO:
    +Display completed phrase if player loses
    +Ensure if they hit not to play again that the app closes
    +If they hit back, they are taken back to the main screen
    +Hangman needs to be drawn, not a simple image
    +Fix exit conditions
 */

class MainActivity : AppCompatActivity(), GameViewFragment.GameListener {

    private var model = GameModel()
    private var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? GameViewFragment
    private var word = "" //I should have named this phrase
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

    /*
    I remember in my first CS course, I was tasked with asking the user
    if they want to restart my C program. My naive solution? just call main again!
    the instructor told me this is always a bad idea.

    Now, here I am, calling Main again.
    https://youtu.be/F4efZIHtiQQ?t=94

    Is it a good idea to do this? I am guessing it is not.
     */
    override fun onBackPressed() {

        if (drawView == null) {
            Log.wtf("BackBtn Pressed", "BackBtn on title screen")
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.chicken)
            builder.setMessage(R.string.exit)
            builder.setPositiveButton(R.string.yes) {
                _, _ ->
                finish()
            }
            builder.setNegativeButton(R.string.no) {
                _, _ ->
                //Do nothing
            }
            builder.show()
        }

        else {
            Log.wtf("Back button pressed", "")
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.chicken)
            builder.setMessage(R.string.title)
            builder.setPositiveButton(R.string.yes) { _, _ ->
                finish()
                startActivity(Intent(this, MainActivity::class.java))
            }
            builder.setNegativeButton(R.string.no) { _, _ ->
                //Do nothing
            }
            builder.show()
        }

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
            // Requirement: Return to title screen if they don't want to play again
            startActivity(Intent(this, MainActivity::class.java))
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun checkWin(){
        if( model.checkWin(word) ){
            gameOverDialog(true)
        }
    }

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
        display = ""

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
        showGuesses.setText("Incorrect: " + display)// Refactor to add placeholder in R.string.incorrect

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

// I no longer want to force user to draw, so I don't need these anymore
//    private fun pauseGuesses() {
//        guessButton.isClickable = false
//    }
//
//    private fun resumeGuesses() {
//        guessButton.isClickable = true
//    }

}
/*
private fun scheduleNotification(
    context: Context,
    delay: Long,
    notificationId: Int
) {//delay is after how much time(in millis) from current time you want to schedule the notification
    val builder = NotificationCompat.Builder(context)
        .setContentTitle(getString(R.string.notification_title))
        .setContentText(getString(R.string.notification_msg))
        .setAutoCancel(true)
        .setSmallIcon(R.drawable.notification_icon_background)
        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

    val intent = Intent(context, MainActivity::class.java)
    val activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    builder.setContentIntent(activity)

    val notification = builder.build()

    val notificationIntent = Intent(context, MyNotificationPublisher::class.java)
    notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId)
    notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification)
    val pendingIntent =
        PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)

    val futureInMillis = SystemClock.elapsedRealtime() + delay
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
}
*/