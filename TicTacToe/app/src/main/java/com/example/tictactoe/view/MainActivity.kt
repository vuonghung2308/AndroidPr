package com.example.tictactoe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.tictactoe.R
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.model.Player
import com.example.tictactoe.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    var gameViewModel: GameViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        promptPlayer()
    }

    fun promptPlayer() {
        val dialog = GameBeginDialog.newInstance(this)
        dialog.show(supportFragmentManager, "dialog_game_begin")
    }

    fun onPlayerSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }

    fun initDataBinding(player1: String, player2: String) {
        val mainBinding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        gameViewModel = ViewModelProviders.of(this)[GameViewModel::class.java]
        gameViewModel?.init(player1, player2)
        mainBinding.gameViewModel = gameViewModel
        setOnWinnerChanged()
    }

    fun setOnWinnerChanged() {
        gameViewModel?.getWinner()?.observe(this, this::onWinnerChanged)
    }

    fun onWinnerChanged(winner: Player?) {
        val name = if (winner == null) "No one" else winner.name
        val dialog = GameEndDialog.newInstance(this, name.toString())
        dialog.show(supportFragmentManager, "dialog_game_end")
    }
}