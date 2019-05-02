package com.example.drawingtesting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.game_view_frag.*

class GameViewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_view_frag, container, false)
    }

    override fun onStart() {
        super.onStart()
        listener?.ready()
    }

    private var listener: GameListener? = null
    interface GameListener {
        fun ready()
    }

    fun registerListener(listener: GameListener) { this.listener = listener}
    fun deregisterListener(listener: GameListener) {
        if(this.listener == listener) {
            this.listener = null
        }
    }
}