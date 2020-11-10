package com.example.tictactoe.viewmodel

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.model.Game
import com.example.tictactoe.model.Player

class GameViewModel : ViewModel() {
    var game: Game? = null
    var cells: ObservableArrayMap<String, String>? = null

    fun init(player1: String, player2: String) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, col: Int) {
        if (game?.cells!![row][col].isEmpty()) {
            game?.cells!![row][col].player = game?.currentPlayer!!
            cells!![row.toString() + col.toString()] = game?.currentPlayer!!.value
            if (game?.hasGameEnd()!!) {
                game?.reset()
            } else game?.switchPlayer()
        }
    }

    fun getWinner(): LiveData<Player> {
        return game?.winner!!
    }
}