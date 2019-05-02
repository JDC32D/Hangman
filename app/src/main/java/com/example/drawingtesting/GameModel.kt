package com.example.drawingtesting

import android.text.method.TextKeyListener.clear
import android.util.Log

/*
Storing the words for the player to guess in this model file seems wrong
as they are strings.
So, I put them in the strings file but now the model is taken out of
the loop when updating the string unless I make a copy of it, which
seems like a bad practice.
 */

class GameModel {
    private val TOTAL_GUESSES = 7
    val incorrectGuesses = arrayListOf<String>()
    private val correctGuesses = arrayListOf<String>()
    private var numGuess = 0
    private var wordNum = 0
    private var testWord = ""
    val isLoser: Boolean
        get() = (numGuess >= TOTAL_GUESSES)

    fun submitGuess(guess: String, valid: Boolean) {
        if (valid) {
            correctGuesses.add(guess)
        } else {
            incorrectGuesses.add(guess)
            numGuess++
        }

    }

    fun checkIfGuessed(guess: String) : String {
        return when (correctGuesses.contains(guess.toLowerCase())) {
            true -> guess
            false -> "_"
        }
    }

    fun checkWin(word: String) : Boolean {
        var correctAns = true
        testWord = ""
        testWord = word.replace("\\s".toRegex(), "")
        testWord.toLowerCase().forEach {
            if (!correctGuesses.contains(it.toString()))
                correctAns = false
        }
            if(correctAns)
                return true
        return false
    }

    fun newGame() {
        incorrectGuesses.clear()
        correctGuesses.clear()
        numGuess = 0
        wordNum = 0
    }

}