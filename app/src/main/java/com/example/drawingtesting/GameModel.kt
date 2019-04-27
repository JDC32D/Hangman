package com.example.drawingtesting

import android.util.Log

class GameModel {
    private val TOTAL_GUESSES = 7
    //private val words = arrayListOf("HELLO WORLD","YEET","YEE HAW")
    private var currentWord = null
    private val guessedLetters = arrayListOf<String>()
    private val correctGuesses = mutableListOf<String>()
    private var numGuess = 0
    private var wordNum = 0

    fun submitGuess(guess: String, valid: Boolean) {
        if (valid) {
            correctGuesses.add(guess)
        } else {
            numGuess++
        }
        guessedLetters.add(guess)
    }

}