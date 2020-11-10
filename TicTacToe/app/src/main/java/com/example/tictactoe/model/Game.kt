package com.example.tictactoe.model

import androidx.lifecycle.MutableLiveData

class Game {

    private val BOARD_SIZE = 3

    var player1: Player? = null
    var player2: Player? = null
    var currentPlayer: Player? = null
    var winner: MutableLiveData<Player>? = null
    var cells: Array<Array<Cell>>? = null

    constructor(playerOne: String, playerTwo: String) {
        player1 = Player(playerOne, "X")
        player2 = Player(playerTwo, "O")
        currentPlayer = player1
        winner = MutableLiveData()
        cells = Array(BOARD_SIZE) {
            Array(BOARD_SIZE) {
                Cell()
            }
        }
    }

    fun hasGameEnd(): Boolean {
        if (hasThreeSameVerticalCells() || hasThreeSameHorizontalCells() || hasThreeSameDiagonalCells()) {
            winner?.value = currentPlayer
            return true
        }
        if (isBoardFull()) {
            winner?.value = null
            return true
        }
        return false
    }

    fun hasThreeSameVerticalCells(): Boolean {
        for (i in 0..BOARD_SIZE - 1) {
            if (areEquals(cells!![i][0], cells!![i][1], cells!![i][2])) {
                return true
            }
        }
        return false
    }

    fun hasThreeSameHorizontalCells(): Boolean {
        for (i in 0..BOARD_SIZE - 1) {
            if (areEquals(cells!![0][i], cells!![1][i], cells!![2][i])) {
                return true
            }
        }
        return false
    }

    fun hasThreeSameDiagonalCells(): Boolean {
        return areEquals(cells!![0][0], cells!![1][1], cells!![2][2]) ||
                areEquals(cells!![0][2], cells!![1][1], cells!![2][0])
    }

    fun areEquals(vararg cells: Cell): Boolean {
        if (cells.isEmpty())
            return false
        val cell0 = cells[0]
        for (cell in cells) {
            if (cell.isEmpty() || cell.player?.value != cell0.player?.value)
                return false
        }
        return true
    }

    fun isBoardFull(): Boolean {
        for (cell1 in cells!!) {
            for (cell2 in cell1) {
                if (cell2.isEmpty())
                    return false
            }
        }
        return true
    }

    fun reset() {
        player1 = null
        player2 = null
        currentPlayer = null
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }
}