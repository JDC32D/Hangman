package com.example.drawingtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.OneShotPreDrawListener.add
import kotlinx.android.synthetic.main.game_view_frag.*
import java.util.*


class MainActivity : AppCompatActivity(), DrawView.DrawViewInterface {

    private var model = GameModel()
    private var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
    private var word = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//        if (gameFragment == null) {
//            gameFragment = GameViewFragment()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_container, gameFragment)
//                .commit()
//        }

//        val startBtn = findViewById<Button>(R.id.startBtn)
//        startBtn.setOnClickListener { button->
////            var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
//            if (gameFragment == null) {
//                gameFragment = GameViewFragment()
//                supportFragmentManager.beginTransaction()
//                    .add(R.id.fragment_container, gameFragment!!)
//                    .commit()
//            }
//
//            button.isEnabled = false
//        }
    }

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

        }
    }

    fun prepWord() {
        word = resources.getStringArray(R.array.words)[Random().nextInt(resources.getStringArray(R.array.words).size-0)+0]
    }

    override fun drawComplete() {
//        Log.e("Activity", "Listening")
//        if( model.checkGuesses() )
//            Log.e("Activity", "Guesses Left")
//        else {
//            supportFragmentManager.beginTransaction()
//                .remove(gameFragment!!)
//                .commit()
//        }
    }

}
