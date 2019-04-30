package com.example.drawingtesting

/*
Storing the words for the player to guess in this model file seems wrong
as they are strings.
So, I put them in the strings file but now the model is taken out of
the loop when updating the string unless I make a copy of it, which
seems like a bad practice.
 */

class GameModel {
    private val TOTAL_GUESSES = 7
    private var currentWord = null
    //private val wordsArray : Array<String> = resources.getStringArray(R.array.words)
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

    fun newGame() {
        guessedLetters.clear()
        correctGuesses.clear()
        numGuess = 0
        wordNum = 0
    }

}