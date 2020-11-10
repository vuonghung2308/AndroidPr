package com.example.tictactoe.model

class Cell() {
    var player: Player? = null
    fun isEmpty(): Boolean {
        if (player == null || player?.value.isNullOrEmpty())
            return true
        return false
    }
}