package com.example.drawingtesting

import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HangmanViewModel : ViewModel() {

    /*
    ViewModel should only hold onto things that are important to the UI
    Game displays:
        portion of word to be guessed based on letters guessed
        list of all letters guessed
        hangman drawing itself
    So, all of these should be stored here.
     */

    //private var wordString = arrayOf<String>()
    //private var userGuesses = arrayOf<String>()

    private val userGuesses: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    fun getGuesses() : LiveData<ArrayList<String>> {
        return userGuesses
    }

    fun updateGuesses() {

    }

    private val wordsToGuess: MutableLiveData<Array<String>> by lazy {
        MutableLiveData<Array<String>>().also {
            loadWordsToGuess()
        }
    }

    fun getWordsToGuess() : LiveData<Array<String>> {
        return wordsToGuess
    }

    fun loadWordsToGuess() {
        //wordString = Resources.getSystem().getString(arrayOf(R.array.words))
        //wordString = Resources.getSystem().getStringArray(R.array.words)
        Resources.getSystem().getStringArray(R.array.words)
    }

//    private var wordToGuess: MutableLiveData<String> by lazy {
//        MutableLiveData<String>()
//    }
//
//    fun getWordToGuess(): LiveData<String> {
//        return wordToGuess
//    }




}