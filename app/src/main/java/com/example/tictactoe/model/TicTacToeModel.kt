package com.example.tictactoe.model

import kotlin.math.E

object TicTacToeModel {
    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
    )

    private var nextPlayer = CIRCLE

    fun resetModel() {
        for (i in 0..4) {
            for (j in 0..4) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

    fun checkWinner(): Boolean {
        //függőlegesen
        if (model[0][0] != EMPTY && model[0][0] == model[0][1] && model[0][0] == model[0][2] &&
            model[0][0] == model[0][3] && model[0][0] == model[0][4]) {
            return true
        } else if (model[1][0] != EMPTY && model[1][0] == model[1][1] && model[1][0] == model[1][2] &&
            model[1][0] == model[1][3] && model[1][0] == model[1][4]) {
            return true
        } else if (model[2][0] != EMPTY && model[2][0] == model[2][1] && model[2][0] == model[2][2] &&
            model[2][0] == model[2][3] && model[2][0] == model[2][4]) {
            return true
        } else if (model[3][0] != EMPTY && model[3][0] == model[3][1] && model[3][0] == model[3][2] &&
            model[3][0] == model[3][3] && model[3][0] == model[3][4]) {
            return true
        } else if (model[4][0] != EMPTY && model[4][0] == model[4][1] && model[4][0] == model[4][2] &&
            model[4][0] == model[4][3] && model[4][0] == model[4][4]) {
            return true
        }
        //vízszintesen
        else if (model[0][0] != EMPTY && model[0][0] == model[1][0] && model[0][0] == model[2][0] &&
            model[0][0] == model[3][0] && model[0][0] == model[4][0]) {
            return true
        } else if (model[0][0] != EMPTY && model[0][0] == model[1][0] && model[0][0] == model[2][0] &&
            model[0][0] == model[3][0] && model[0][0] == model[4][0]) {
            return true
        } else if (model[0][1] != EMPTY && model[0][1] == model[1][1] && model[0][1] == model[2][1] &&
            model[0][1] == model[3][1] && model[0][1] == model[4][1]) {
            return true
        } else if (model[0][2] != EMPTY && model[0][2] == model[1][2] && model[0][2] == model[2][2] &&
            model[0][2] == model[3][2] && model[0][2] == model[4][2]) {
            return true
        } else if (model[0][3] != EMPTY && model[0][3] == model[1][3] && model[0][3] == model[2][3] &&
            model[0][3] == model[3][3] && model[0][3] == model[4][3]) {
            return true
        } else if (model[0][4] != EMPTY && model[0][4] == model[1][4] && model[0][4] == model[2][4] &&
            model[0][4] == model[3][4] && model[0][4] == model[4][4]) {
            return true
        }
        //átlósan
        else if (model[0][0] != EMPTY && model[0][0] == model[1][1] && model[0][0] == model[2][2] &&
            model[0][0] == model[3][3] && model[0][0] == model[4][4]) {
            return true
        } else if (model[4][0] != EMPTY && model[4][0] == model[3][1] && model[4][0] == model[2][2] &&
            model[4][0] == model[1][3] && model[4][0] == model[0][4]) {
            return true
        }
        return false
    }
}