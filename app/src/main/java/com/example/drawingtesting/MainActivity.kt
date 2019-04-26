package com.example.drawingtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

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

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener { button->
            var gameFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (gameFragment == null) {
                gameFragment = GameViewFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, gameFragment)
                    .commit()
            }
            button.isEnabled = false
        }
    }

}
