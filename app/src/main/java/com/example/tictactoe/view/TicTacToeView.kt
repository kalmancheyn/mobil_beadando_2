package com.example.tictactoe.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.tictactoe.MainActivity
import com.example.tictactoe.model.TicTacToeModel


class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()
    private var paintBorderLine: Paint = Paint()
    private var paintLineCross: Paint = Paint()
    private var paintLineCircle: Paint = Paint()
    private var boolWin = true

    init {

        paintBackground.color = Color.parseColor("#A9A9A9")
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.parseColor("#808080")
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintBorderLine.color = Color.parseColor("#808080")
        paintBorderLine.style = Paint.Style.STROKE
        paintBorderLine.strokeWidth = 10f

        paintLineCross.color = Color.BLACK
        paintLineCross.style = Paint.Style.STROKE
        paintLineCross.strokeWidth = 5f

        paintLineCircle.color = Color.WHITE
        paintLineCircle.style = Paint.Style.STROKE
        paintLineCircle.strokeWidth = 5f


    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        canvas!!.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(),60f,60f, paintBackground)

        drawGameArea(canvas)

        drawPlayers(canvas)
    }

    private fun drawGameArea(canvas: Canvas) {
        canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(),60f,60f, paintBorderLine)

        canvas.drawLine(0f, (height / 5).toFloat(), width.toFloat(), (height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (2 * height / 5).toFloat(), width.toFloat(), (2 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (3 * height / 5).toFloat(), width.toFloat(), (3 * height / 5).toFloat(), paintLine)
        canvas.drawLine(0f, (4 * height / 5).toFloat(), width.toFloat(), (4 * height / 5).toFloat(), paintLine)

        canvas.drawLine((width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(), paintLine)
        canvas.drawLine((2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(), paintLine)
        canvas.drawLine((3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(), paintLine)
        canvas.drawLine((4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(), paintLine)
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 5 + width / 10).toFloat()
                    val centerY = (j * height / 5 + height / 10).toFloat()
                    val radius = height / 10-5

                    canvas.drawCircle(centerX, centerY, radius.toFloat()-20, paintLineCircle)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine(
                        (i * width / 5).toFloat()+20, (j * height / 5).toFloat()+20,
                        ((i + 1) * width / 5).toFloat()-20, ((j + 1) * height / 5).toFloat()-20, paintLineCross
                    )

                    canvas.drawLine(
                        ((i + 1) * width / 5).toFloat()-20, (j * height / 5)+20.toFloat(),
                        (i * width / 5).toFloat()+20, ((j + 1) * height / 5).toFloat()-20, paintLineCross
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)
            if (tX < 5 && tY < 5 && TicTacToeModel.getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {
                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.getNextPlayer())
                if (TicTacToeModel.checkWinner()) {
                    if (boolWin) {
                        var winner: String
                        if (TicTacToeModel.getNextPlayer().toInt() == 1) {
                            winner = "Kör"
                        } else {
                            winner = "Iksz"
                        }
                        (context as MainActivity).showWinner("A győztes $winner")
                        refreshModel()
                        boolWin = false
                    }
                } else {
                    refreshModel()
                    showNextPlayer()
                }
            }
        }
        return true
    }

    public fun resetGame() {
        TicTacToeModel.resetModel()
        invalidate()
        (context as MainActivity).showWinner("")
        showNextPlayer()
        boolWin = true
    }

    fun showNextPlayer () {
        var next = "Kör"
        if (TicTacToeModel.getNextPlayer() == TicTacToeModel.CROSS) {
            next = "Iksz"
        }
        (context as MainActivity).showText("A következő játékos $next")
    }

    fun refreshModel () {
        TicTacToeModel.changeNextPlayer()
        invalidate()
    }

}
